package com.platform.publicze_platform.Service.ServiceImpl;

import com.platform.publicze_platform.Service.MinioService;
import io.minio.*;
import io.minio.errors.MinioException;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Service
public class MinioServiceImpl implements MinioService {
    private final MinioClient minioClient;
    public MinioServiceImpl(@Value("${minio.endpoint}") String endpoint,
                        @Value("${minio.accesskey}") String accessKey,
                        @Value("${minio.secretkey}") String secretKey) throws MinioException {
        this.minioClient = MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
    };
    public void uploadFile(MultipartFile file, String bucketName, String objectName)
    {
        // 上传文件到Minio
        try (InputStream inputStream = file.getInputStream()) {
            boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!isExist) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName) // 这里即为存储路径，即完整的对象名称（包括文件名）
                    .stream(inputStream,file.getSize(),-1) //
                    .contentType(file.getContentType()) // 根据需要设置内容类型，此处为二进制流类型，可根据文件类型调整，如"image/jpeg"等。
                    .build());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    };
}

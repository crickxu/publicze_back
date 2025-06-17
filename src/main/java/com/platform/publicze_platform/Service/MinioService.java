package com.platform.publicze_platform.Service;

import io.minio.errors.MinioException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MinioService {
    void uploadFile(MultipartFile file,String bucketName, String objectName);
}

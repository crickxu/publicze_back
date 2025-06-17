package com.platform.publicze_platform.Service.ServiceImpl;

import com.platform.publicze_platform.Service.FastDSFClient;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.csource.fastdfs.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FastDSFClientImpl implements FastDSFClient {
    private static Logger logger = LoggerFactory.getLogger(FastDSFClientImpl.class);

    /**
     * 初始化加载 FastDFS的TrackerServer配置
     */
    static {
        try {
            //String filePath = FastDSFClientImpl.class.getResource("/fdfs_client.conf").getPath();// 获得配置文件的路
            //String filePath = new ClassPathResource("fdfs_client.conf").getFile().getAbsolutePath();
            String path = new ClassPathResource("fdfs_client.conf").getPath();

            ClientGlobal.init(path);
        } catch (Exception e) {
            logger.error("FastDFS 初始化失败", e);
        }
    }
    /**
     * 文件上传
     * @param
     * @return
     */
    public String[] upload(byte[] content, String fileName,String author) {
        // 获取文件的作者
        NameValuePair[] meta_list = new NameValuePair[1];
        meta_list[0] = new NameValuePair("author", author);
        // 接收返回数据
        String[] uploadResults = null;
        StorageClient storageClient = null;
        String ext =fileName.split("\\.")[1];
        try {
            // 创建 StorageClient 客户端对象
            storageClient = getTrackerClient();

            /**
             * 文件上传
             * 1) 文件字节数组
             * 2) 文件扩展名
             * 3) 文件作者
             */
            uploadResults = storageClient.upload_file(content, ext, meta_list);
        } catch (Exception e) {
            logger.error("上传文件失败: " + ext, e);
        }

        if (uploadResults ==null && storageClient != null) {
            logger.error("上传文件错误，错误码: " + storageClient.getErrorCode());
        }
        // 获取组名
        String groupName = uploadResults[0];
        // 获取文件存储路径
        String remoteFileName = uploadResults[1];
        return uploadResults;
    }
    /**
     * 文件下载
     * @param groupName
     * @param remoteFileName
     * @return
     */
    public InputStream downStreamFile(String groupName, String remoteFileName) {
        try {
            // 创建 StorageClient
            StorageClient storageClient = getTrackerClient();
            // 下载文件
            byte[] fileByte = storageClient.download_file(groupName, remoteFileName);
            ByteArrayInputStream ins = new ByteArrayInputStream(fileByte);
            return ins;
        } catch (Exception e) {
            logger.error("从FastDFS获取文件失败", e);
        }
        return null;
    }
    public int downFile(String groupName, String remoteFileName,String filePath)
    {
        try
        {
            StorageClient storageClient = getTrackerClient();
            int num = storageClient.download_file(groupName,remoteFileName,filePath);
            return num;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            logger.error("从FastDFS获取文件失败", e);
        }
        catch (MyException e){e.printStackTrace();logger.error("从FastDFS获取文件失败", e);}
        return 0;
    }
    /**
     * 文件删除
     * @param groupName
     * @param remoteFileName
     */
    public void deleteFile(String groupName, String remoteFileName) {
        try {
            // 创建 StorageClient
            StorageClient storageClient = getTrackerClient();
            // 删除文件
            int i = storageClient.delete_file(groupName, remoteFileName);
        } catch (Exception e) {
            logger.error("从FastDFS中文件删除失败", e);
        }
    }
    /**
     * 获取Storage 客户端
     * @return
     */
    private StorageClient getTrackerClient() {
        TrackerServer trackerServer = getTrackerServer();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        return storageClient;
    }
    /**
     * 获取 Tracker
     * @return
     */
    private TrackerServer getTrackerServer() {
        try {
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            return trackerServer;
        } catch (Exception e) {
            logger.error("获取Tracker 失败", e);
        }
        return null;
    }
}

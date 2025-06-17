package com.platform.publicze_platform.Service;

import java.io.InputStream;

public interface FastDSFClient {
    String[] upload(byte[] content, String fileName,String author);
    int downFile(String groupName, String remoteFileName,String filePath);
    InputStream downStreamFile(String groupName, String remoteFileName);
    void deleteFile(String groupName, String remoteFileName);
}

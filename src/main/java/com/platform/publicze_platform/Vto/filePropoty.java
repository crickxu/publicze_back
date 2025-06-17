package com.platform.publicze_platform.Vto;

import com.sun.xml.internal.ws.developer.Serialization;
import lombok.Data;

import java.util.List;

@Data
@Serialization
public class filePropoty {
    public int fileType;
    public String id;
    public String title;
    public boolean isMainImg;
    public String discrip;
    public String companyNo;
    public String groupName;
    public List<proParam> proparams;
    public List<String> filePaths;
}

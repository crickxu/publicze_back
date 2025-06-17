package com.platform.publicze_platform.Vto;

import com.sun.xml.internal.ws.developer.Serialization;
import lombok.Data;

@Data
@Serialization
public class imgPropoty {
    public String name;
    public String title;
    public int isMainImg;
    public String discrip;
    public String companyNo;
    public String groupName;
    public String filePath;
}

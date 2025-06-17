package com.platform.publicze_platform.Vto;

import com.sun.xml.internal.ws.developer.Serialization;
import lombok.Data;

@Data
@Serialization
public class videoPropoty {
    public String name;
    public String title;
    public String discrip;
    public String companyNo;
    public String groupName;
    public String videoPath;
}

package com.platform.publicze_platform.Vto;

import com.sun.xml.internal.ws.developer.Serialization;
import lombok.Data;

import java.io.InputStream;

@Data
@Serialization
public class StreamFileInfo {
    public String title;
    public String streamType;
    public InputStream imgStream;
}

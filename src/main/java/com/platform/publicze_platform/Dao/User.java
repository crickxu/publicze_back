package com.platform.publicze_platform.Dao;

import java.util.Date;
import lombok.Data;

@Data
public class User {
    private Integer id;

    private String userNo;

    private String name;

    private String passwd;

    private String contact;

    private String addr;

    private Date creattime;

    private Date updatetime;
}
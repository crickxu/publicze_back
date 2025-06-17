package com.platform.publicze_platform.Dao;

import java.util.Date;
import lombok.Data;

@Data
public class CompanyInfo {
    public Integer id;

    /**
     * 公司编号
     */
    public String companyNo;

    /**
     * 用户编号
     */
    public String userNo;

    /**
     * 公司名称
     */
    public String name;

    /**
     * 统一信用代码
     */
    public String creditCode;

    /**
     * 0:国企1：央企2：民营企业
     */
    public Integer natureId;

    public String natureName;
    /**
     * 公司规模
     */
    public Integer scale;

    /**
     * 所在省
     */
    public String provinceAddr;
    /**
     * 所在省名称
     */
    public String provinceAddrName;

    /**
     * 所在市
     */
    public String cityAddr;
    /**
     * 所在市名称
     */
    public String cityAddrName;

    /**
     * 具体地址
     */
    public String contact;

    /**
     * 公司介绍
     */
    public String introduction;

    /**
     * 联系号码
     */
    public String mobileNum;

    /**
     * 图片路径
     */
    public String imgPath;

    /**
     * 发布类型(0-企业资源 1-企业融资 2-企业合作 3-企业代理，可多种组合)
     */
    public Integer eventType;

    /**
     * 所属行业代码
     */
    public String industry;

    /**
     * 所属二级行业代码
     */
    public String subIndustry;
    /**
     * 所属二级行业名称
     */
    public String subIndustryName;
    /**
     * 所属三级行业代码
     */
    public String thrIndustry;
    /**
     * 所属三级行业名称
     */
    public String thrIndustryName;
    /**
     * 行业名称
     */
    public String industryName;
    /**
     * 创建时间
     */
    public Date createTime;
}
package com.platform.publicze_platform.Dao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

@ApiModel(value = "com-platform-publicze_platform-Dao-ImgCompanyInfo")
@Data
public class ImgCompanyInfo {
    @ApiModelProperty(value = "")
    public Integer id;

    /**
     * 公司编号
     */
    @ApiModelProperty(value = "公司编号")
    public String companyNo;

    /**
     * 图片存储所在组
     */
    @ApiModelProperty(value = "图片存储所在组")
    public String groupName;

    /**
     * 图片路径
     */
    @ApiModelProperty(value = "图片路径")
    public String imgPath;

    /**
     * 图片标题
     */
    @ApiModelProperty(value = "图片标题")
    public String imgTitle;

    /**
     * 图片说明
     */
    @ApiModelProperty(value = "图片说明")
    public String discription;

    /**
     * 是否可用
     */
    @ApiModelProperty(value = "是否可用")
    public Boolean enable;

    /**
     * 是否是主图片
     */
    @ApiModelProperty(value = "是否是主图片")
    public Boolean isMainImg;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    public Date createTime;
}
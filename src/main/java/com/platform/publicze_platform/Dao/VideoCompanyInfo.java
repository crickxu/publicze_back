package com.platform.publicze_platform.Dao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

@ApiModel(value = "com-platform-publicze_platform-Dao-VideoCompanyInfo")
@Data
public class VideoCompanyInfo {
    @ApiModelProperty(value = "")
    public Integer id;

    /**
     * 公司编号
     */
    @ApiModelProperty(value = "公司编号")
    public String companyNo;

    /**
     * 视频存储所在组
     */
    @ApiModelProperty(value = "视频存储所在组")
    public String groupName;

    /**
     * 视频路径
     */
    @ApiModelProperty(value = "视频路径")
    public String videoPath;

    /**
     * 视频标题
     */
    @ApiModelProperty(value = "视频标题")
    public String videoTitle;

    /**
     * 视频说明
     */
    @ApiModelProperty(value = "视频说明")
    public String discription;

    /**
     * 是否可用
     */
    @ApiModelProperty(value = "是否可用")
    public Boolean enable;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    public Date createTime;
}
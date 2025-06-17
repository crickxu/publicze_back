package com.platform.publicze_platform.Dao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.util.List;

import lombok.Data;

@ApiModel(value = "com-platform-publicze_platform-Dao-Region")
@Data
public class Region {
    @ApiModelProperty(value = "")
    public Integer id;

    /**
     * 地区编号
     */
    @ApiModelProperty(value = "地区编号")
    public String regionNo;

    /**
     * 父地区编号
     */
    @ApiModelProperty(value = "父地区编号")
    public String parenRegionNo;

    /**
     * 是否热门城市
     */
    @ApiModelProperty(value = "是否热门城市")
    public Boolean isPopular;

    /**
     * 地区名称
     */
    @ApiModelProperty(value = "地区名称")
    public String name;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    public String remark;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    public Date createTime;

    public List<Region> children;
}
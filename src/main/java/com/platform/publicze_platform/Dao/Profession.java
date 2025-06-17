package com.platform.publicze_platform.Dao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.util.List;

import lombok.Data;

@ApiModel(value="com-platform-publicze_platform-Dao-Profession")
@Data
public class Profession {
    @ApiModelProperty(value="")
    public Integer id;

    /**
    * 行业编号
    */
    @ApiModelProperty(value="行业编号")
    public String professionNo;

    /**
    * 行业等级
    */
    @ApiModelProperty(value="行业等级")
    public Integer lever;

    /**
    * 父行业编号
    */
    @ApiModelProperty(value="父行业编号")
    public String professionParentNo;

    /**
    * 行业名称
    */
    @ApiModelProperty(value="行业名称")
    public String name;

    /**
    * 是否可用
    */
    @ApiModelProperty(value="是否可用")
    public Boolean isEnable;

    /**
    * 备注
    */
    @ApiModelProperty(value="备注")
    public String remark;

    /**
    * 创建时间
    */
    @ApiModelProperty(value="创建时间")
    public Date createTime;

    public List<Profession> children;
}
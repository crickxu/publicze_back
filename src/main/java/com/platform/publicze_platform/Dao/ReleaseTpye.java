package com.platform.publicze_platform.Dao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

@ApiModel(value = "com-platform-publicze_platform-Dao-ReleaseTpye")
@Data
public class ReleaseTpye {
    @ApiModelProperty(value = "")
    private Integer id;

    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    private Integer type;

    /**
     * 类型名称
     */
    @ApiModelProperty(value = "类型名称")
    private String typeName;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
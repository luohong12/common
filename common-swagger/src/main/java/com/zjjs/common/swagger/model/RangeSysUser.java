/*
 * Copyright (C) 2018 Zhejiang xiaominfo Technology CO.,LTD.
 * Official Web Site: http://www.xiaominfo.com.
 */

package com.zjjs.common.swagger.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author <a href="mailto:xiaoymin@foxmail.com">xiaoymin@foxmail.com</a>
 * 2022/5/1 13:32
 * @since knife4j-spring-boot-demo 1.0
 */
@Data
public class RangeSysUser {
    @ApiModelProperty(value = "用户id",hidden = true)
    private Long userId;

    @ApiModelProperty(value = "部门id")
    private Long depId;


    @ApiModelProperty(value = "range属性", allowableValues = "range[0, 100]", example = "1")
    private String name;


    @ApiModelProperty(value = "枚举示例", allowableValues = ".25, .5, 1", example = "1")
    private String name1;
}

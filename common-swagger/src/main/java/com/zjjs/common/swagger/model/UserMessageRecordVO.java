/*
 * Copyright (C) 2022 Zhejiang xiaominfo Technology CO.,LTD.
 * All rights reserved.
 * Official Web Site: http://www.xiaominfo.com.
 * Developer Web Site: http://open.xiaominfo.com.
 */
package com.zjjs.common.swagger.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @since:knife4j-spring-boot27-demo
 * @auth <a href="xiaoymin@foxmail.com">xiaoymin@foxmail.com</a>
 * 2022/12/3 13:51
 */
@Data
public class UserMessageRecordVO {
    @ApiModelProperty(value = "用户id",required = true)
    @NotBlank(message = "不能为空")
    private Long userId;

    @ApiModelProperty(value = "部门id")
    @NotEmpty(message = "不能为空")
    @Max(2000)
    private Long depId;
}

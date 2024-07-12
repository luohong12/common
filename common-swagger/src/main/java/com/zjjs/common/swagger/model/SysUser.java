/*
 * Copyright (C) 2018 Zhejiang xiaominfo Technology CO.,LTD.
 * Official Web Site: http://www.xiaominfo.com.
 */

package com.zjjs.common.swagger.model;

import io.swagger.annotations.ApiModelProperty;


/**
 * @author <a href="mailto:xiaoymin@foxmail.com">xiaoymin@foxmail.com</a>
 * 2022/5/1 13:32
 * @since:knife4j-spring-boot-demo 1.0
 */
public class SysUser {
    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "部门id")
    private Long depId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDepId() {
        return depId;
    }

    public void setDepId(Long depId) {
        this.depId = depId;
    }
}

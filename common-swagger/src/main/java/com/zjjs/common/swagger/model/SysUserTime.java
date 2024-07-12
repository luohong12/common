/*
 * Copyright (C) 2018 Zhejiang xiaominfo Technology CO.,LTD.
 * Official Web Site: http://www.xiaominfo.com.
 */

package com.zjjs.common.swagger.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;


/**
 * @author <a href="mailto:xiaoymin@foxmail.com">xiaoymin@foxmail.com</a>
 * 2022/5/1 13:32
 * @since:knife4j-spring-boot-demo 1.0
 */
@ApiModel("系统用户时间表")
public class SysUserTime {
    @ApiModelProperty(value = "用户id",required = true)
    @NotBlank(message = "不能为空")
    private Long userId;

    @ApiModelProperty(value = "部门id")
    @NotEmpty(message = "不能为空")
    @Max(2000)
    private Long depId;

    @ApiModelProperty(value = "日期")
    private LocalDateTime nowTime;

    public LocalDateTime getNowTime() {
        return nowTime;
    }

    public void setNowTime(LocalDateTime nowTime) {
        this.nowTime = nowTime;
    }

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

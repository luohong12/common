/*
 * Copyright (C) 2022 Zhejiang xiaominfo Technology CO.,LTD.
 * All rights reserved.
 * Official Web Site: http://www.xiaominfo.com.
 * Developer Web Site: http://open.xiaominfo.com.
 */
package com.zjjs.common.swagger.model;

/**
 * @since:knife4j-spring-boot27-demo
 * @auth <a href="xiaoymin@foxmail.com">xiaoymin@foxmail.com</a>
 * 2022/9/4 19:55
 */
public enum StatusEnum {
    success(1, "success"),
    fail(2, "fail");

    private int code;
    private String value;

    @Override
    public String toString() {
        return "StatusEnum{" +
                "code=" + code +
                ", value='" + value + '\'' +
                '}';
    }

    StatusEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

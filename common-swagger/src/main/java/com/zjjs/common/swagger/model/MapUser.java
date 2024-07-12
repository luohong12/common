package com.zjjs.common.swagger.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

/**
 * @author <a href="xiaoymin@foxmail.com">xiaoymin@foxmail.com</a>
 * 2022/12/3 16:21
 * @since:knife4j-spring-boot27-demo
 */
@Data
public class MapUser {

    @ApiModelProperty(value = "pid-测试id")
    private Integer pid;

    @ApiModelProperty(value = "用户列表")
    private HashMap<String, List<SysUser>> userLists;
}

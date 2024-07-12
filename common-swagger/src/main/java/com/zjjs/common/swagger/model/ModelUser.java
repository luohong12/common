/*
 * Copy right © 2022 浙江力石科技股份有限公司 All Rights Reserved.
 * Official Web Site: http://lishiots.com
 */
package com.zjjs.common.swagger.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author <a href="xiaoymin@foxmail.com">xiaoymin@foxmail.com</a>
 * 2022/12/3 21:41
 * @since:knife4j-spring-boot27-demo
 */
@Data
public class ModelUser {
    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "系统用户")
    private SysUser sysUser;

    /**
     * 教师 id 列表。
     */
    @ApiModelProperty(value = "教师 id 列表。", required = true)
    @NotNull(message = "请选择教师")
    private List<Long> ids;

    @ApiModelProperty(value = "年龄")
    private Long age;


}

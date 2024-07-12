package com.zjjs.common.core.web.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * ClassName:BasePageParam
 * Package:com.zjjs.common.core.web.domain
 * Description:分页基础参数
 * Datetime: 2024/7/11:10:37
 * Author: 石德斌
 */
@Data
@ApiModel("分页基础参数")
public class BasePageParam implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(
            value = "当前页",
            required = true
    )
    @NotNull
    private Integer pageNum;
    @ApiModelProperty(
            value = "每页条数",
            required = true
    )
    @NotNull
    private Integer pageSize;
    @ApiModelProperty("排序列")
    private String orderByColumn;
    @ApiModelProperty("排序方式")
    private String isAsc = "asc";

}

package com.zjjs.code.gen.param;

import com.zjjs.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ClassName:DbInfoParam
 * Package:com.zjjs.code.gen.param
 * Description:
 * Datetime: 2024/7/4:14:39
 * Author: 石德斌
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel("代码生成数据库查询参数")
@Data
public class DbInfoParam extends BaseEntity {
    @ApiModelProperty("数据库表名")
    private String tableName;
    @ApiModelProperty("数据库表备注")
    private String tableComment;
}

package com.zjjs.code.gen.param;

import com.zjjs.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ClassName:GenParam
 * Package:com.zjjs.code.gen.param
 * Description:
 * Datetime: 2024/7/8:9:12
 * Author: 石德斌
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel("代码生成请求参数")
@Data
public class GenParam extends BaseEntity {
    /** 作者 */
    @ApiModelProperty("作者")
    public String author;

    /** 生成包路径 */
    @ApiModelProperty("生成包路径")
    public String packageName;

    /** 自动去除表前缀，默认是false */
    @ApiModelProperty("自动去除表前缀，默认是false")
    public boolean autoRemovePre;

    /** 表前缀(类名不会包含表前缀) */
    @ApiModelProperty("表前缀(类名不会包含表前缀) ")
    public String tablePrefix;
    @ApiModelProperty("表名，以,隔开")
    public String tables;
    /** 使用的模板（crud单表操作 tree树表操作 sub主子表操作） */
    @ApiModelProperty("使用的模板（crud单表操作 tree树表操作 sub主子表操作） ")
    private String tplCategory="crud";
    /** 关联父表的表名 */
    @ApiModelProperty("关联父表的表名")
    private String subTableName;

    /** 本表关联父表的外键名 */
    @ApiModelProperty("本表关联父表的外键名")
    private String subTableFkName;
}

package com.zjjs.code.gen.domain.sysMenu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.ExcelIgnore;


/**
 * @author shidebin
 * @date 2024-07-11
 */
@ApiModel(value = "菜单表", description = "菜单表")
@Data
public class SysMenuBO {

    @ApiModelProperty(value = "主键id")
    @ExcelProperty("主键id")
    private String id;

    @ApiModelProperty(value = "父菜单id")
    @ExcelProperty("父菜单id")
    private String parentId;

    @ApiModelProperty(value = "菜单编号")
    @ExcelProperty("菜单编号")
    private String menuCode;

    @ApiModelProperty(value = "菜单名称")
    @ExcelProperty("菜单名称")
    private String menuName;

    @ApiModelProperty(value = "菜单图标")
    @ExcelProperty("菜单图标")
    private String menuIcon;

    @ApiModelProperty(value = "菜单地址")
    @ExcelProperty("菜单地址")
    private String menuUrl;

    @ApiModelProperty(value = "组件地址")
    @ExcelProperty("组件地址")
    private String component;

    @ApiModelProperty(value = "排序")
    @ExcelProperty("排序")
    private Long seq;

    @ApiModelProperty(value = "是否启用: 0-启用 1-停用")
    @ExcelProperty("是否启用(0 启用 1 停用)")
    private Long status;

    @ApiModelProperty(value = "创建时间")
    @ExcelProperty("创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @ExcelProperty("修改时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @ApiModelProperty(value = "菜单类型: 1-内部 2-外部")
    @ExcelProperty("菜单类型: 1-内部 2-外部")
    private String menuType;

    @ApiModelProperty(value = "是否为系统菜单: 0-否 1-是")
    @ExcelProperty("是否为系统菜单: 0-否 1-是")
    private Long isSystem;

    @ApiModelProperty(value = "创建人")
    @ExcelProperty("创建人")
    private String createBy;

    @ApiModelProperty(value = "是否隐藏: 0-否 1-是")
    @ExcelProperty("是否隐藏: 0-否 1-是")
    private Long isHide;

    @ApiModelProperty(value = "是否开启权限: 0-否 1-是")
    @ExcelProperty("是否开启权限: 0-否 1-是")
    private Long isAcl;

    @ApiModelProperty(value = "是否缓存: 0-否 1-是")
    @ExcelProperty("是否缓存: 0-否 1-是")
    private Long isCache;

    @ApiModelProperty(value = "$column.columnComment")
    @ExcelProperty("$column.columnComment")
    private String skipUrl;

    @ApiModelProperty(value = "$column.columnComment")
    @ExcelProperty("$column.columnComment")
    private String dataPath;
}

package com.zjjs.code.gen.domain.sysMenu;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zjjs.common.core.web.domain.BaseEntity;

/**
 * 菜单对象 sys_menu
 *
 * @author shidebin
 * @date 2024-07-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Alias("SysMenu")
public class SysMenu extends BaseEntity{

private static final long serialVersionUID=1L;

    /** 主键id */
    private String id;
    /** 父菜单id */
    private String parentId;
    /** 菜单编号 */
    private String menuCode;
    /** 菜单名称 */
    private String menuName;
    /** 菜单图标 */
    private String menuIcon;
    /** 菜单地址 */
    private String menuUrl;
    /** 组件地址 */
    private String component;
    /** 排序 */
    private Long seq;
    /** 是否启用: 0-启用 1-停用 */
    private Long status;
    /** 菜单类型: 1-内部 2-外部 */
    private String menuType;
    /** 是否为系统菜单: 0-否 1-是 */
    private Long isSystem;
    /** 是否隐藏: 0-否 1-是 */
    private Long isHide;
    /** 是否开启权限: 0-否 1-是 */
    private Long isAcl;
    /** 是否缓存: 0-否 1-是 */
    private Long isCache;
    /** $column.columnComment */
    private String skipUrl;
    /** $column.columnComment */
    private String dataPath;
}

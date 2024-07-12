package com.zjjs.code.gen.mapper.sysMenu;

import java.util.List;

import com.zjjs.code.gen.domain.sysMenu.SysMenu;
import com.zjjs.code.gen.domain.sysMenu.SysMenuPageParam;

/**
 * 菜单Mapper接口
 *
 * @author shidebin
 * @date 2024-07-11
 */
public interface SysMenuMapper {
    /**
     * 查询菜单
     *
     * @param id 菜单主键
     * @return 菜单
     */
    public SysMenu selectSysMenuById(String id);

    /**
     * 查询菜单列表
     *
     * @param sysMenuPageParam 菜单
     * @return 菜单集合
     */
    public List<SysMenu> selectSysMenuList(SysMenuPageParam sysMenuPageParam);

    /**
     * 新增菜单
     *
     * @param sysMenu 菜单
     * @return 结果
     */
    public int insertSysMenu(SysMenu sysMenu);

    /**
     * 修改菜单
     *
     * @param sysMenu 菜单
     * @return 结果
     */
    public int updateSysMenu(SysMenu sysMenu);

    /**
     * 删除菜单
     *
     * @param id 菜单主键
     * @return 结果
     */
    public int deleteSysMenuById(String id);

    /**
     * 批量删除菜单
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysMenuByIds(String[] ids);
}

package com.zjjs.code.gen.service.sysMenu;

import com.zjjs.code.gen.domain.sysMenu.SysMenuBO;
import com.zjjs.code.gen.domain.sysMenu.SysMenuPageParam;
import com.zjjs.code.gen.domain.sysMenu.SysMenuParam;

import java.util.List;

/**
 * 菜单Service接口
 *
 * @author shidebin
 * @date 2024-07-11
 */
public interface ISysMenuService {
    /**
     * 查询菜单
     *
     * @param id 菜单主键
     * @return 菜单
     */
    SysMenuBO selectSysMenuById(String id);

    /**
     * 查询菜单列表
     *
     * @param sysMenuPageParam 菜单
     * @return 菜单集合
     */
    List<SysMenuBO> selectSysMenuList(SysMenuPageParam sysMenuPageParam);

    /**
     * 新增菜单
     *
     * @param sysMenuParam 菜单
     * @return 结果
     */
    int insertSysMenu(SysMenuParam sysMenuParam);

    /**
     * 修改菜单
     *
     * @param sysMenuParam 菜单
     * @return 结果
     */
    int updateSysMenu(SysMenuParam sysMenuParam);

    /**
     * 批量删除菜单
     *
     * @param ids 需要删除的菜单主键集合
     * @return 结果
     */
    int deleteSysMenuByIds(String[] ids);

    /**
     * 删除菜单信息
     *
     * @param id 菜单主键
     * @return 结果
     */
    int deleteSysMenuById(String id);
}

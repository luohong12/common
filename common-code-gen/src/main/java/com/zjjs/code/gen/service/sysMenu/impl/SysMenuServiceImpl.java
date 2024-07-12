package com.zjjs.code.gen.service.sysMenu.impl;

import java.util.List;
import com.zjjs.common.core.utils.DateUtils;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.zjjs.code.gen.mapper.sysMenu.SysMenuMapper;
import com.zjjs.code.gen.domain.sysMenu.SysMenu;
import com.zjjs.code.gen.domain.sysMenu.SysMenuBO;
import com.zjjs.code.gen.domain.sysMenu.SysMenuParam;
import com.zjjs.code.gen.domain.sysMenu.SysMenuPageParam;
import com.zjjs.code.gen.domain.sysMenu.SysMenuConverter;
import com.zjjs.code.gen.service.sysMenu.ISysMenuService;
import lombok.extern.slf4j.Slf4j;

/**
 * 菜单Service业务层处理
 *
 * @author shidebin
 * @date 2024-07-11
 */
@Service
@Slf4j
public class SysMenuServiceImpl implements ISysMenuService {
    @Resource
    private SysMenuMapper sysMenuMapper;

    /**
     * 查询菜单
     *
     * @param id 菜单主键
     * @return 菜单
     */
    @Override
    public SysMenuBO selectSysMenuById(String id) {
        SysMenu sysMenu = sysMenuMapper.selectSysMenuById(id);
        return SysMenuConverter.INSTANCE.doToBo(sysMenu);
    }

    /**
     * 查询菜单列表
     *
     * @param sysMenuPageParam 菜单
     * @return 菜单
     */
    @Override
    public List<SysMenuBO> selectSysMenuList(SysMenuPageParam sysMenuPageParam) {
        List<SysMenu> sysMenuList = sysMenuMapper.selectSysMenuList(sysMenuPageParam);
        return SysMenuConverter.INSTANCE.doListToBoList(sysMenuList);
    }

    /**
     * 新增菜单
     *
     * @param sysMenuParam 菜单
     * @return 结果
     */
    @Override
    public int insertSysMenu(SysMenuParam sysMenuParam) {
            SysMenu sysMenu = SysMenuConverter.INSTANCE.createParamToDo(sysMenuParam);
            sysMenu.setCreateTime(DateUtils.getNowDate());
            return sysMenuMapper.insertSysMenu(sysMenu);
    }

    /**
     * 修改菜单
     *
     * @param sysMenuParam 菜单
     * @return 结果
     */
    @Override
    public int updateSysMenu(SysMenuParam sysMenuParam) {
        SysMenu sysMenu = SysMenuConverter.INSTANCE.createParamToDo(sysMenuParam);
        sysMenu.setUpdateTime(DateUtils.getNowDate());
        return sysMenuMapper.updateSysMenu(sysMenu);
    }

    /**
     * 批量删除菜单
     *
     * @param ids 需要删除的菜单主键
     * @return 结果
     */
    @Override
    public int deleteSysMenuByIds(String[] ids) {
        return sysMenuMapper.deleteSysMenuByIds(ids);
    }

    /**
     * 删除菜单信息
     *
     * @param id 菜单主键
     * @return 结果
     */
    @Override
    public int deleteSysMenuById(String id) {
        return sysMenuMapper.deleteSysMenuById(id);
    }
}

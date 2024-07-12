package com.zjjs.code.gen.controller.sysMenu;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zjjs.common.log.annotation.Log;
import com.zjjs.common.log.enums.BusinessType;
import com.zjjs.common.security.annotation.RequiresPermissions;
import com.zjjs.code.gen.domain.sysMenu.SysMenu;
import com.zjjs.code.gen.domain.sysMenu.SysMenuBO;
import com.zjjs.code.gen.domain.sysMenu.SysMenuParam;
import com.zjjs.code.gen.domain.sysMenu.SysMenuPageParam;
import com.zjjs.code.gen.service.sysMenu.ISysMenuService;
import com.zjjs.common.core.web.controller.BaseController;
import com.zjjs.common.core.web.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import java.util.Objects;
import javax.annotation.Resource;
import java.net.URLEncoder;
import com.alibaba.excel.EasyExcel;
import com.zjjs.common.core.web.page.TableDataInfo;

/**
 * 菜单Controller
 *
 * @author shidebin
 * @date 2024-07-11
 */
@RestController
@RequestMapping("/menu")
@Slf4j
@Api(tags = {"菜单管理"})
public class SysMenuController extends BaseController {
    @Resource
    private ISysMenuService sysMenuService;

    /**
     * 查询菜单列表
     */
    @RequiresPermissions("gen:menu:list")
    @GetMapping("/list")
    @ApiOperation(value = "查询菜单列表")
    public TableDataInfo page(SysMenuPageParam sysMenuPageParam) {
        startPage();
        List<SysMenuBO> list = sysMenuService.selectSysMenuList(sysMenuPageParam);
        return getDataTable(list);
    }

    /**
     * 导出菜单列表
     */
    @RequiresPermissions("gen:menu:export")
    @Log(title = "菜单" , businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    @ApiOperation(value = "导出菜单列表")
    public void export(HttpServletResponse response, SysMenuPageParam sysMenuPageParam) {
        try {
            //设置相关参数
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("数据字典", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename="+ fileName + ".xlsx");
            List<SysMenuBO> list = sysMenuService.selectSysMenuList(sysMenuPageParam);
            //写出
            EasyExcel.write(response.getOutputStream(), SysMenuBO.class).sheet("菜单列表").doWrite(list);
        } catch (Exception e) {
            log.error("导出菜单列表失败",e);
        }
    }

    /**
     * 获取菜单详细信息
     */
    @RequiresPermissions("gen:menu:query")
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取菜单详细信息")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(sysMenuService.selectSysMenuById(id));
    }

    /**
     * 新增菜单
     */
    @RequiresPermissions("gen:menu:add")
    @Log(title = "菜单" , businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation(value = "新增菜单")
    public AjaxResult add(@RequestBody SysMenuParam sysMenuParam) {
        return toAjax(sysMenuService.insertSysMenu(sysMenuParam));
    }

    /**
     * 修改菜单
     */
    @RequiresPermissions("gen:menu:edit")
    @Log(title = "菜单" , businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation(value = "修改菜单")
    public AjaxResult edit(@RequestBody SysMenuParam sysMenuParam) {
        return toAjax(sysMenuService.updateSysMenu(sysMenuParam));
    }

    /**
     * 删除菜单
     */
    @RequiresPermissions("gen:menu:remove")
    @Log(title = "菜单" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @ApiOperation(value = "删除菜单")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(sysMenuService.deleteSysMenuByIds(ids));
    }
}

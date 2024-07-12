package com.zjjs.code.gen.controller;

import com.alibaba.fastjson.JSON;
import com.zjjs.code.gen.domain.GenTable;
import com.zjjs.code.gen.domain.GenTableColumn;
import com.zjjs.code.gen.param.DbInfoParam;
import com.zjjs.code.gen.param.GenParam;
import com.zjjs.code.gen.service.IGenTableColumnService;
import com.zjjs.code.gen.service.IGenTableService;
import com.zjjs.common.log.annotation.Log;
import com.zjjs.common.log.enums.BusinessType;
import com.zjjs.common.core.text.Convert;
import com.zjjs.common.core.utils.Assert;
import com.zjjs.common.core.web.controller.BaseController;
import com.zjjs.common.core.web.domain.AjaxResult;
import com.zjjs.common.core.web.page.TableDataInfo;
import com.zjjs.common.security.annotation.RequiresPermissions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 代码生成 操作处理
 *
 * @author zjjs
 */
@RequestMapping("/gen")
@Api(tags = "代码生成处理器")
@RestController
public class GenController extends BaseController {
    @Resource
    private Map<String, IGenTableService> genTableServiceMap;

    @Resource
    private Map<String,IGenTableColumnService> genTableColumnServiceMap;
    @Value("${gen.use.db:mysql}")
    String useDb;
    /**
     * 查询代码生成列表
     */
    @RequiresPermissions("tool:gen:list")
    @GetMapping("/list")
    @ApiOperation("查询代码生成列表")
    public TableDataInfo genList(GenTable genTable) {
        startPage();
        List<GenTable> list = genTableUseDb(useDb).selectGenTableList(genTable);
        return getDataTable(list);
    }

    /**
     * 查询数据库列表
     */
    @RequiresPermissions("tool:gen:list")
    @GetMapping("/db/list")
    @ApiOperation("查询数据库中表名和备注")
    public TableDataInfo dataList(DbInfoParam dbInfoParam) {
        startPage();
        List<GenTable> list = genTableUseDb(useDb).selectDbTableList(dbInfoParam);
        return getDataTable(list);
    }

    /**
     * 查询数据表字段列表
     */
    @GetMapping(value = "/column/{tableId}")
    @ApiOperation("查询数据表字段列表")
    public TableDataInfo columnList(Long tableId) {
        TableDataInfo dataInfo = new TableDataInfo();
        List<GenTableColumn> list = genTableColumnServiceMap.get("genTableColumn"+useDb).selectGenTableColumnListByTableId(tableId);
        dataInfo.setRows(list);
        dataInfo.setTotal(list.size());
        return dataInfo;
    }

    /**
     * 导入表结构（保存）
     */
    @RequiresPermissions("tool:gen:import")
    @Log(title = "导入表结构（保存）", businessType = BusinessType.IMPORT)
    @PostMapping("/importTable")
    @ApiOperation("导入表结构（保存）")
    public AjaxResult importTableSave(GenParam genParam) {
        String tables = genParam.getTables();
        String[] tableNames = Convert.toStrArray(tables);
        // 查询表信息
        List<GenTable> tableList = genTableUseDb(useDb).selectDbTableListByNames(tableNames);
        genTableUseDb(useDb).importGenTable(genParam,tableList);
        return success();
    }

    /**
     * 修改保存代码生成业务
     */
    @RequiresPermissions("tool:gen:edit")
    @Log(title = "代码生成", businessType = BusinessType.UPDATE)
    @ApiOperation("修改保存代码生成业务")
    @PutMapping
    public AjaxResult editSave(@Validated @RequestBody GenTable genTable) {
        genTableUseDb(useDb).validateEdit(genTable);
        genTableUseDb(useDb).updateGenTable(genTable);
        return success();
    }

    /**
     * 删除代码生成
     */
    @RequiresPermissions("tool:gen:remove")
    @Log(title = "代码生成", businessType = BusinessType.DELETE)
    @DeleteMapping("/{tableIds}")
    @ApiOperation("删除代码生成")
    public AjaxResult remove(@PathVariable Long[] tableIds) {
        genTableUseDb(useDb).deleteGenTableByIds(tableIds);
        return success();
    }

    /**
     * 预览代码
     */
    @RequiresPermissions("tool:gen:preview")
    @GetMapping("/preview/{tableId}")
    @ApiOperation("预览代码")
    public AjaxResult preview(@PathVariable("tableId") Long tableId) throws IOException {
        Map<String, String> dataMap = genTableUseDb(useDb).previewCode(tableId);
        return success(dataMap);
    }

    /**
     * 生成代码（下载方式）
     */
    @RequiresPermissions("tool:gen:code")
    @Log(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/download/{tableName}")
    @ApiOperation("生成代码（下载方式）")
    public void download(HttpServletResponse response, @PathVariable("tableName") String tableName) throws IOException {
        byte[] data = genTableUseDb(useDb).downloadCode(tableName);
        genCode(response, data);
    }

    /**
     * 生成代码（自定义路径）
     */
    @RequiresPermissions("tool:gen:code")
    @Log(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/genCode/{tableName}")
    @ApiOperation("代码生成")
    public AjaxResult genCode(@PathVariable("tableName") String tableName) {
        genTableUseDb(useDb).generatorCode(tableName);
        return success();
    }

    /**
     * 同步数据库
     */
    @RequiresPermissions("tool:gen:edit")
    @Log(title = "代码生成", businessType = BusinessType.UPDATE)
    @GetMapping("/synchDb/{tableName}")
    @ApiOperation("同步数据库")
    public AjaxResult synchDb(@PathVariable("tableName") String tableName) {
        genTableUseDb(useDb).synchDb(tableName);
        return success();
    }

    /**
     * 批量生成代码
     */
    @RequiresPermissions("tool:gen:code")
    @Log(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/batchGenCode")
    @ApiOperation("批量生成代码")
    public void batchGenCode(HttpServletResponse response, String tables) throws IOException {
        String[] tableNames = Convert.toStrArray(tables);
        byte[] data = genTableUseDb(useDb).downloadCode(tableNames);
        genCode(response, data);
    }

    /**
     * 生成zip文件
     */
    private void genCode(HttpServletResponse response, byte[] data) throws IOException {
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"zjjs.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }


    private IGenTableService genTableUseDb(String useDb) {
        String name ="genTable"+useDb;
        IGenTableService iGenTableService = genTableServiceMap.get(name);
        Assert.isNull(iGenTableService,"没有对应生成策略,只支持"+ JSON.toJSONString(genTableServiceMap.keySet()));
        return iGenTableService;
    }

}

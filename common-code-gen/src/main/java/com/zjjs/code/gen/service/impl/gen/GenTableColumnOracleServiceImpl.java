package com.zjjs.code.gen.service.impl.gen;

import com.zjjs.code.gen.domain.GenTableColumn;
import com.zjjs.code.gen.mapper.GenTableColumnOracleMapper;
import com.zjjs.code.gen.service.IGenTableColumnService;
import com.zjjs.common.core.text.Convert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 业务字段 服务层实现
 *
 * @author zjjs
 */
@Service("genTableColumnOracle")
@Slf4j
public class GenTableColumnOracleServiceImpl implements IGenTableColumnService {
    @Resource
    private GenTableColumnOracleMapper genTableColumnOracleMapper;

    /**
     * 查询业务字段列表
     *
     * @param tableId 业务字段编号
     * @return 业务字段集合
     */
    @Override
    public List<GenTableColumn> selectGenTableColumnListByTableId(Long tableId) {
        return genTableColumnOracleMapper.selectGenTableColumnListByTableId(tableId);
    }

    /**
     * 新增业务字段
     *
     * @param genTableColumn 业务字段信息
     * @return 结果
     */
    @Override
    public int insertGenTableColumn(GenTableColumn genTableColumn) {
        return genTableColumnOracleMapper.insertGenTableColumn(genTableColumn);
    }

    /**
     * 修改业务字段
     *
     * @param genTableColumn 业务字段信息
     * @return 结果
     */
    @Override
    public int updateGenTableColumn(GenTableColumn genTableColumn) {
        return genTableColumnOracleMapper.updateGenTableColumn(genTableColumn);
    }

    /**
     * 删除业务字段对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGenTableColumnByIds(String ids) {
        return genTableColumnOracleMapper.deleteGenTableColumnByIds(Convert.toLongArray(ids));
    }
}
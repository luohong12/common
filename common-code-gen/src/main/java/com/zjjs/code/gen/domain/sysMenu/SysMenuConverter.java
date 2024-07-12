package com.zjjs.code.gen.domain.sysMenu;

import com.zjjs.common.core.convert.BaseConverter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author shidebin
 * @date 2024-07-11
 */
@Mapper(componentModel = "spring")
public interface SysMenuConverter extends BaseConverter<SysMenu, SysMenuBO, SysMenuParam, SysMenuPageParam> {

    SysMenuConverter INSTANCE = Mappers.getMapper(SysMenuConverter.class);
}

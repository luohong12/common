//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.zjjs.common.core.convert;

import java.util.List;

public interface BaseConverter<DO, BO, Param, PageParam> {
    DO createParamToDo(Param param);

    DO modifyParamToDo(PageParam modifyParam);

    DO boToDo(BO bo);

    BO doToBo(DO d0);

    List<BO> doListToBoList(List<DO> doList);

    List<DO> boListToDtoList(List<BO> boList);
}

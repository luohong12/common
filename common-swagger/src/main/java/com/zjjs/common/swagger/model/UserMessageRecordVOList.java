/*
 * Copyright (C) 2022 Zhejiang xiaominfo Technology CO.,LTD.
 * All rights reserved.
 * Official Web Site: http://www.xiaominfo.com.
 * Developer Web Site: http://open.xiaominfo.com.
 */
package com.zjjs.common.swagger.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @since:knife4j-spring-boot27-demo
 * @auth <a href="xiaoymin@foxmail.com">xiaoymin@foxmail.com</a>
 * 2022/12/3 13:51
 */
@Data
@ApiModel(description = "快显消息列表")
public class UserMessageRecordVOList extends SysUser{

    @ApiModelProperty(value = "快显消息列表 " +
            "数据结构是Map<String,Map<Long, List<UserMessageRecordVO>>> 的格式;" +
            "第一层Map，用于区分发信和收信，Key为‘to’（发信），‘from’（收信）" +
            "第二层Map Key是消息ID，Value是对应消息" +
            "最内层List 是该条消息的不同收件人和消息内容")
    private Map<String, Map<Long, List<UserMessageRecordVO>>> msgList;


    @ApiModelProperty(value = "很豪华")
    private Map<String, Map<Long, Map<String,Map<String,List<List<UserMessageRecordVO>>>>>> msgNumList;


    /**
     * Gets msgList.
     *
     * @return Value of msgList.
     */
    public Map<String, Map<Long, List<UserMessageRecordVO>>> getMsgList() {
        return msgList;
    }

    /**
     * Sets new msgList.
     *
     * @param msgList New value of msgList.
     */
    public void setMsgList(Map<String, Map<Long, List<UserMessageRecordVO>>> msgList) {
        this.msgList = msgList;
    }
}

package com.zjjs.common.core.utils;

import com.zjjs.common.core.exception.base.BaseException;

import java.util.Objects;

/**
 * ClassName:Assert
 * Package:com.zjjs.common.core.utils
 * Description:
 * Datetime: 2024/7/4:16:36
 * Author: 石德斌
 */
public class Assert {
    public static void isNull(Object o,String msg)throws BaseException{
        if(Objects.isNull(o)){
           throw new BaseException(msg);
        }
    }
}

package com.zjjs.code.gen.controller;

import com.zjjs.code.gen.mapper.TestMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * ClassName:TestController
 * Package:com.zjjs.code.gen.controller
 * Description:
 * Datetime: 2024/7/3:9:38
 * Author: 石德斌
 */
@RequestMapping("/health")
@RestController
public class TestController {
    @Resource
    TestMapper testMapper;
    @GetMapping
    public Date health(){
        return testMapper.getDate();
    }
}

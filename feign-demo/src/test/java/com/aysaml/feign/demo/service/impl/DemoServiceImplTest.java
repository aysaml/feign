package com.aysaml.feign.demo.service.impl;

import com.aysaml.feign.demo.FeignDemoApplication;
import com.aysaml.feign.demo.service.DemoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author wangning40
 * @date 2019-11-13
 */
@SpringBootTest(classes = FeignDemoApplication.class)
@SpringBootConfiguration
class DemoServiceImplTest {

    @Autowired
    private DemoService demoService;

    @Test
    void hello() {
        demoService.hello("aysaml");
    }
}
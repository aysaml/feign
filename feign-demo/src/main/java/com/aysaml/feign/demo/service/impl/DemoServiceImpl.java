package com.aysaml.feign.demo.service.impl;

import com.aysaml.common.feign.client.DemoFeignClient;
import com.aysaml.common.feign.entity.FeignResult;
import com.aysaml.feign.demo.service.DemoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Demo service to call say hello implement.
 *
 * @author wangning
 * @date 2019-11-13
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Resource
    private DemoFeignClient demoFeignClient;

    @Override
    public void hello(String name) {
        FeignResult<String> result = demoFeignClient.hello(name);
        System.out.println(result);
    }
}

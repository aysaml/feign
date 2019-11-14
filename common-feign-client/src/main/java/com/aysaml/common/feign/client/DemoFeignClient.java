package com.aysaml.common.feign.client;

import com.aysaml.common.feign.entity.FeignResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Demo of Feign client.
 *
 * @author wangning40
 * @date 2019-11-14
 */
@FeignClient(url = "${aysaml.common.feign.demo.url}", name = "demo")
public interface DemoFeignClient {

    /**
     * Say hello.
     *
     * @param name
     * @return
     */
    @GetMapping(value = "/hello")
    FeignResult<String> hello(@RequestParam(value = "name") String name);
}

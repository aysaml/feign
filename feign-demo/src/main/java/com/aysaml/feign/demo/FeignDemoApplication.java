package com.aysaml.feign.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Spring boot startup class.
 *
 * @author wangning
 * @date 2019-11-13
 */
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.aysaml.common.feign.client"})
public class FeignDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignDemoApplication.class, args);
    }

}

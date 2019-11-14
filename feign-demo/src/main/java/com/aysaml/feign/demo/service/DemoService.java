package com.aysaml.feign.demo.service;

/**
 * Demo service to call say hello.
 *
 * @author wangning
 * @date 2019-11-13
 */
public interface DemoService {

    /**
     * hello.
     *
     * @param name
     */
    void hello(String name);
}

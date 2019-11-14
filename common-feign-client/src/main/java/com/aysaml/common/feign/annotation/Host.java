package com.aysaml.common.feign.annotation;

import java.lang.annotation.*;

/**
 * Feign client host annotation.
 *
 * @author wangning
 * @date 2019-10-29
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Host {
  String value();
}

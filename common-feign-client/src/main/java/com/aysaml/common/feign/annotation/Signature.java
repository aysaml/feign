package com.aysaml.common.feign.annotation;

import java.lang.annotation.*;

/**
 * Signature annotation.
 *
 * @author wangning
 * @date 2019-10-29
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Signature {

  String appKey();

  String appSecret();
}

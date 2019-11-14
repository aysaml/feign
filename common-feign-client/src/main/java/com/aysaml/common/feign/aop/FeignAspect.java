package com.aysaml.common.feign.aop;

import com.aysaml.common.feign.annotation.Host;
import com.aysaml.common.feign.annotation.Signature;
import com.aysaml.common.feign.constant.Constants;
import com.aysaml.common.feign.request.SimpleRequestAttributes;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * Feign annotation Aspect class.
 *
 * @author wangning
 * @date 2019-10-29
 */
@Aspect
@Component
public class FeignAspect {

  private static final String PLACEHOLDER_BEGIN = "${";

  private static final String PLACEHOLDER_END = "}";

  @Autowired private Environment environment;

  @Pointcut("execution(* com.aysaml.common.feign.client..*.*(..))")
  public void init() {}

  @Around("init()")
  public Object handleHost(ProceedingJoinPoint joinPoint) throws Throwable {
    RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
    attributes = initializeRequestAttributes(attributes);
    Class clazz = joinPoint.getTarget().getClass();
    Host host = this.findHost(clazz);
    if (null != host) {
      attributes.setAttribute(Constants.FEIGN_HOST, this.resolvePlaceHolder(host.value()), 0);
    }
    Signature signature = this.findSignature(clazz);
    if (null != signature) {
      String appKey = this.resolvePlaceHolder(signature.appKey());
      String appSecret = this.resolvePlaceHolder(signature.appSecret());
      attributes.setAttribute(Constants.FEIGN_APPKEY, appKey, 0);
      attributes.setAttribute(Constants.FEIGN_APPSECRET, appSecret, 0);
    }
    try {
      return joinPoint.proceed();
    } finally {
      attributes.removeAttribute(Constants.FEIGN_HOST, 0);
      attributes.removeAttribute(Constants.FEIGN_APPKEY, 0);
      attributes.removeAttribute(Constants.FEIGN_APPSECRET, 0);
    }
  }

  private RequestAttributes initializeRequestAttributes(RequestAttributes attributes) {
    if (attributes == null) {
      attributes = new SimpleRequestAttributes();
      RequestContextHolder.setRequestAttributes(attributes);
    }
    return attributes;
  }

  private Signature findSignature(Class clazz) {
    Class[] interfaces = clazz.getInterfaces();
    for (int i = 0; i < interfaces.length; i++) {
      Class clazz1 = interfaces[i];
      Signature signature = (Signature) clazz1.getAnnotation(Signature.class);
      if (signature != null) {
        return signature;
      }
    }
    return null;
  }

  private Host findHost(Class clazz) {
    Class[] interfaces = clazz.getInterfaces();
    for (int i = 0; i < interfaces.length; i++) {
      Class clazz1 = interfaces[i];
      Host host = (Host) clazz1.getAnnotation(Host.class);
      if (null != host) {
        return host;
      }
    }
    return null;
  }

  private String resolvePlaceHolder(String str) {
    if (str.startsWith(PLACEHOLDER_BEGIN) && str.endsWith(PLACEHOLDER_END)) {
      String key = str.substring(2, str.length() - 1);
      return this.environment.containsProperty(key) ? this.environment.getProperty(key) : "";
    } else {
      return str;
    }
  }
}

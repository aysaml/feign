package com.aysaml.common.feign.interceptor;

import com.aysaml.common.feign.constant.Constants;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.http.protocol.HTTP;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * Host interceptor , to add host into header.
 *
 * @author wangning
 * @date 2019-10-29
 */
public class HostRequestInterceptor implements RequestInterceptor {

  @Override
  public void apply(RequestTemplate requestTemplate) {
    RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
    if (null != attributes) {
      String host = (String) attributes.getAttribute(Constants.FEIGN_HOST, 0);
      if (null != host) {
        requestTemplate.header(HTTP.TARGET_HOST, host);
      }
    }
  }
}

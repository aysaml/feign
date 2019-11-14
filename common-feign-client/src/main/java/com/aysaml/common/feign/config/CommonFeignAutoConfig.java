package com.aysaml.common.feign.config;

import com.aysaml.common.feign.aop.FeignAspect;
import com.aysaml.common.feign.interceptor.HostRequestInterceptor;
import com.aysaml.common.feign.interceptor.SignatureRequestInterceptor;
import com.aysaml.common.feign.request.RequestAttributeHystrixConcurrencyStrategy;
import feign.Logger;
import feign.codec.Decoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * Common feign autoConfiguration.
 *
 * @author wangning
 * @date 2019-10-29
 */
@Configuration
public class CommonFeignAutoConfig {

  public CommonFeignAutoConfig() {}

  @Bean
  public RequestAttributeHystrixConcurrencyStrategy requestAttributeHystrixConcurrencyStrategy() {
    return new RequestAttributeHystrixConcurrencyStrategy();
  }

  @Bean
  public HostRequestInterceptor hostRequestInterceptor() {
    return new HostRequestInterceptor();
  }

  @Bean
  public SignatureRequestInterceptor signatureRequestInterceptor() {
    return new SignatureRequestInterceptor();
  }

  @Bean
  public FeignAspect feignAspect() {
    return new FeignAspect();
  }

  @Bean
  Logger.Level feignLoggerLevel() {
    return Logger.Level.FULL;
  }

  @Bean
  public Decoder feignDecoder() {
    return new ResponseEntityDecoder(new SpringDecoder(feignHttpMessageConverter()));
  }

  public ObjectFactory<HttpMessageConverters> feignHttpMessageConverter() {
    final HttpMessageConverters httpMessageConverters =
        new HttpMessageConverters(new PhpMappingJackson2HttpMessageConverter());
    return () -> httpMessageConverters;
  }

  public class PhpMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
    PhpMappingJackson2HttpMessageConverter() {
      List<MediaType> mediaTypes = new ArrayList<>();
      mediaTypes.add(MediaType.valueOf(MediaType.TEXT_HTML_VALUE + ";charset=UTF-8"));
      mediaTypes.add(MediaType.APPLICATION_JSON);
      setSupportedMediaTypes(mediaTypes);
    }
  }
}

package com.aysaml.common.feign.request;

import org.springframework.web.context.request.RequestAttributes;

import java.util.concurrent.ConcurrentHashMap;

/**
 * The custom RequestAttributes.
 *
 * @author wangning
 * @date 2019-10-29
 */
public class SimpleRequestAttributes implements RequestAttributes {

  private ConcurrentHashMap<String, Object> attributes = new ConcurrentHashMap<>();

  @Override
  public Object getAttribute(String key, int scope) {
    return this.attributes.get(key);
  }

  @Override
  public void setAttribute(String key, Object value, int scope) {
    this.attributes.put(key, value);
  }

  @Override
  public void removeAttribute(String key, int scope) {
    this.attributes.remove(key);
  }

  @Override
  public String[] getAttributeNames(int i) {
    return this.attributes.keySet().toArray(new String[this.attributes.size()]);
  }

  @Override
  public void registerDestructionCallback(String s, Runnable runnable, int i) {}

  @Override
  public Object resolveReference(String s) {
    return null;
  }

  @Override
  public String getSessionId() {
    return null;
  }

  @Override
  public Object getSessionMutex() {
    return null;
  }
}

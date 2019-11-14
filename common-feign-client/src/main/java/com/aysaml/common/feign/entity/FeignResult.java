package com.aysaml.common.feign.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * common feign client response.
 *
 * @author wangning
 * @date 2019-10-28
 */
@Data
public class FeignResult<T> implements Serializable {

  private int code;
  private String message;
  private T data;

  public FeignResult() {
  }
}

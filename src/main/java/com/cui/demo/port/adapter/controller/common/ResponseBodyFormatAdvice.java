package com.cui.demo.port.adapter.controller.common;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class ResponseBodyFormatAdvice implements ResponseBodyAdvice {

  @Override
  public boolean supports(MethodParameter methodParameter, Class aClass) {
    return true;
  }

  @Override
  public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType,
      Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
    if (! (o instanceof  Response)) {
      return new Response<>(o);
    }

    return o;
  }
}

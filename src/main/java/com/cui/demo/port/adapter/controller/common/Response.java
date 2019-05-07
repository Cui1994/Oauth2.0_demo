package com.cui.demo.port.adapter.controller.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Response<T> {

  private Integer code = HttpStatus.OK.value();
  private String msg = "Success";
  private T data;

  public Response(T data) {
    this.data = data;
  }

  public Response(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
  }

}
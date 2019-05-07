package com.cui.demo.port.adapter.controller.common;

import com.cui.demo.exc.SystemException;
import com.cui.demo.exc.UserException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author tao
 */
@ControllerAdvice
public class ControllerErrorAdvice {

  @ExceptionHandler(UserException.class)
  @ResponseBody
  public Response userExceptionHandler(HttpServletRequest request, UserException exception) {
    return Response.builder()
        .code(exception.getCode())
        .msg(exception.getMessage())
        .build();
  }

  @ExceptionHandler(SystemException.class)
  @ResponseBody
  public Response systemExceptionHandler(HttpServletRequest request, SystemException execption) {
    // todo: remove print
    execption.printStackTrace();
    return Response.builder()
        .code(execption.getCode())
        .msg(execption.getMessage())
        .build();
  }

  @ExceptionHandler(Exception.class)
  @ResponseBody
  public Response exceptionHandler(HttpServletRequest request, Exception exception) {
    exception.printStackTrace();
    return Response.builder()
        .code(500)
        .msg("未知错误")
        .build();
  }

}

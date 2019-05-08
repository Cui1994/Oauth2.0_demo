package com.cui.demo.port.adapter.controller.common;

import com.cui.demo.exc.ErrorMessage;
import com.cui.demo.exc.SystemException;
import com.cui.demo.exc.UserException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author tao
 */
@ControllerAdvice
public class ControllerErrorAdvice {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseBody
  public Response argsNotValidExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException exception) {
    BindingResult bindingResult = exception.getBindingResult();
    StringBuilder sb = new StringBuilder();
    List<ObjectError> allErrors = bindingResult.getAllErrors();

    for (ObjectError allError : allErrors) {
      sb.append(allError.getDefaultMessage()).append(". ");
    }

    return Response.builder()
        .code(ErrorMessage.PARAM_CAN_NOT_BE_EMPTY.getCode())
        .msg(sb.toString())
        .build();
  }

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

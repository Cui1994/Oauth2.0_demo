package com.cui.demo.exc;

import lombok.Getter;

public enum ErrorMessage {

  /**
   * token 校验失败
   */
  TOKEN_CHECK_FAIL(401, "token 校验失败"),
  PARAM_CAN_NOT_BE_EMPTY(402, "参数不能为空");


  @Getter
  private Integer code;
  private String msg;

  ErrorMessage(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public UserException createUserExc() {
    return new UserException(code, msg);
  }

  public UserException createUserExc(String msg) {
    return new UserException(code, msg);
  }
}

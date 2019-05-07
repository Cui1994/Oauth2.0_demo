package com.cui.demo.exc;

public enum ErrorMessage {

  /**
   * token 校验失败
   */
  TOKEN_CHECK_FAIL(401, "token 校验失败");

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

package com.cui.demo.exc;

import lombok.Data;

@Data
public class UserException extends Exception {

  private Integer code = 400;
  private String message = "Server user error.";

  public UserException() {
  }

  public UserException(String message) {
    this.message = message;
  }

  public UserException(Integer code, String message) {
    this.code = code;
    this.message = message;
  }

}

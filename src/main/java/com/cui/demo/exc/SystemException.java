package com.cui.demo.exc;

import lombok.Data;

@Data
public class SystemException extends Exception{
  private final Integer code = 500;
  private String message = "Server error.";

  public SystemException() {
  }

  public SystemException(String message) {
    this.message = message;
  }
}

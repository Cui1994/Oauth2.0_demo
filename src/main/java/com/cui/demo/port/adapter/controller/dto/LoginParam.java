package com.cui.demo.port.adapter.controller.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LoginParam {

  @NotNull(message = "账号不能为空")
  private String mobile;

  @NotNull(message = "密码不能为空")
  private String password;

}

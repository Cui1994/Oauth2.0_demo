package com.cui.demo.port.adapter.controller.dto;


import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterParam {

  @NotNull(message = "手机号不能为空")
  private String mobile;

  @NotNull(message = "请输入姓名")
  private String name;

  @NotNull(message = "密码不能为空")
  private String password;
}

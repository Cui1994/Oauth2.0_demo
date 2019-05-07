package com.cui.demo.domain.model.account;

import com.cui.demo.exc.SystemException;
import com.cui.demo.utils.MD5Util;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author tao
 */
@Data
@Builder
@AllArgsConstructor
public class Account {

  private Long id;

  @NotNull
  private String mobile;

  @NotNull
  private String name;

  @NotNull
  private String password;

  @NotNull
  private String salt;

  private String saltGen() {
    return UUID.randomUUID().toString().substring(0, 6);
  }

  private String passwordGen(String inputPassword) throws SystemException {
    return MD5Util.md5Gen(this.salt + inputPassword);
  }

  public Account(String mobile, String name, String inputPassword) throws SystemException {
    this.mobile = mobile;
    this.name = name;
    this.salt = saltGen();
    this.password = passwordGen(inputPassword);
  }

  public Boolean verify(String inputPassword) throws SystemException {
    return this.password.equals(passwordGen(inputPassword));
  }

  // todo:think 登录是否为 Account 领域职责
}

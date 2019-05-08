package com.cui.demo.port.adapter.controller;

import com.cui.demo.application.AccountApplication;
import com.cui.demo.exc.SystemException;
import com.cui.demo.exc.UserException;
import com.cui.demo.port.adapter.controller.annotation.TokenHandler;
import com.cui.demo.port.adapter.controller.dto.AccountDTO;
import com.cui.demo.port.adapter.controller.dto.LoginInfoDTO;
import com.cui.demo.port.adapter.controller.dto.AccountVerifyInfo;
import com.cui.demo.port.adapter.controller.dto.LoginParam;
import com.cui.demo.port.adapter.controller.dto.RegisterParam;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

  @Autowired
  private AccountApplication accountApplication;

  @PostMapping("register")
  public LoginInfoDTO registerAccount(@Valid @RequestBody RegisterParam registerParam)
      throws SystemException, UserException {
    System.out.println(registerParam);
    return accountApplication.register(registerParam.getMobile(), registerParam.getName(), registerParam.getPassword());
  }

  @PostMapping("login")
  public LoginInfoDTO login(@RequestBody LoginParam loginParam)
      throws SystemException, UserException {
    return accountApplication.login(loginParam.getMobile(), loginParam.getPassword());
  }

  @RequestMapping("info")
  @TokenHandler
  public AccountDTO getInfo(@NotNull AccountVerifyInfo verifyInfo)
      throws SystemException, UserException {
    return accountApplication.getAccountById(verifyInfo.getAccountId());
  }

}

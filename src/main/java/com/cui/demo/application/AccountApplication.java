package com.cui.demo.application;

import com.cui.demo.domain.model.account.Account;
import com.cui.demo.domain.model.account.IAccountRepo;
import com.cui.demo.domain.model.passport.LoginPassportService;
import com.cui.demo.exc.SystemException;
import com.cui.demo.exc.UserException;
import com.cui.demo.port.adapter.controller.dto.AccountDTO;
import com.cui.demo.port.adapter.controller.dto.LoginInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 账户应用层，不包含业务规则，只进行校验以及协调领域层动作。
 * @author tao
 */
@Service
public class AccountApplication {

  @Autowired
  IAccountRepo accountRepo;

  @Autowired
  LoginPassportService loginPassportService;

  public LoginInfoDTO register(String mobile, String name, String password)
      throws UserException, SystemException {
    Account existAccount = accountRepo.getByMobile(mobile);
    if (existAccount != null) {
      throw new UserException("用户已存在");
    }

    Account account = new Account(mobile, name, password);
    accountRepo.insert(account);

    String token = loginPassportService.createToken(account.getId());

    return LoginInfoDTO.builder()
        .accountId(account.getId())
        .mobile(account.getMobile())
        .name(account.getName())
        .token(token)
        .build();
  }

  public LoginInfoDTO login(String mobile, String inputPassword) throws UserException, SystemException {
    Account account = accountRepo.getByMobile(mobile);
    if (account == null) {
      throw new UserException("用户不存在");
    }

    if (!account.verify(inputPassword)) {
      throw new UserException("用户名或密码错误");
    }

    String token = loginPassportService.createToken(account.getId());

    return LoginInfoDTO.builder()
        .accountId(account.getId())
        .mobile(account.getMobile())
        .name(account.getName())
        .token(token)
        .build();
  }

  public AccountDTO getAccountById(Long accountId) {
    Account account = accountRepo.getById(accountId);
    if (account == null) {
      return null;
    }

    return AccountDTO.builder()
        .id(account.getId())
        .name(account.getName())
        .mobile(account.getMobile())
        .build();
  }

}

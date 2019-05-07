package com.cui.demo.port.adapter.persistence;

import com.cui.demo.domain.model.account.Account;
import com.cui.demo.domain.model.account.IAccountRepo;
import com.cui.demo.port.adapter.persistence.tb.TbAccount;
import com.cui.demo.port.adapter.persistence.tb.TbAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tao
 */
@Service
public class AccountRepo implements IAccountRepo {

  @Autowired
  TbAccountMapper tbAccountMapper;

  private Account convertAccount(TbAccount tbAccount) {
    return Account.builder()
        .id(tbAccount.getId())
        .mobile(tbAccount.getMobile())
        .password(tbAccount.getPassword())
        .name(tbAccount.getName())
        .salt(tbAccount.getSalt())
        .build();
  }

  @Override
  public Account getByMobile(String mobile) {
    TbAccount tbAccount = tbAccountMapper.selectByMobile(mobile);
    if (tbAccount == null) {
      return null;
    }
    return convertAccount(tbAccount);
  }

  @Override
  public Account getById(Long accountId) {
    TbAccount tbAccount = tbAccountMapper.selectByPrimaryKey(accountId);
    if (tbAccount == null) {
      return null;
    }

    return convertAccount(tbAccount);
  }

  @Override
  public void insert(Account account) {

    TbAccount record = TbAccount.builder()
        .mobile(account.getMobile())
        .name(account.getName())
        .password(account.getPassword())
        .salt(account.getSalt())
        .isDeleted(0)
        .build();

    Long id = tbAccountMapper.insert(record);
    account.setId(id);
  }
}

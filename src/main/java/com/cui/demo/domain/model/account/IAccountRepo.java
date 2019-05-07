package com.cui.demo.domain.model.account;

/**
 * AccountRepository
 * @author tao
 */
public interface IAccountRepo {

  /**
   * 由手机号查询账号
   * @param mobile 手机号
   * @return 账号实体
   */
  Account getByMobile(String mobile);

  /**
   * accountId 查询账号
   * @param accountId
   * @return 账号实体
   */
  Account getById(Long accountId);

  /**
   * 持久化账号对象
   * @param account
   */
  void insert(Account account);
}

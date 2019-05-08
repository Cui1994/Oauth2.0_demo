package com.cui.demo.domain.model.account;

import static org.junit.Assert.*;

import com.cui.demo.DemoApplication;
import com.cui.demo.exc.SystemException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class AccountTest {

  @Test
  public void testCreate() throws SystemException {

    Account account1 = Account.builder()
        .id(1L)
        .name("test1")
        .mobile("1122")
        .password("123abc")
        .salt("1234567")
        .build();

    assertEquals(account1.getPassword(), "123abc");

  }
}
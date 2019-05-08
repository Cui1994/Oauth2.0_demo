package com.cui.demo.domain.model.passport;

import static org.junit.Assert.*;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cui.demo.DemoApplication;
import com.cui.demo.conf.JWTLoginPassportConf;
import com.cui.demo.exc.ErrorMessage;
import com.cui.demo.exc.UserException;
import com.cui.demo.utils.DateUtil;
import java.util.Date;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class LoginPassportServiceTest {

  @Autowired
  private JWTLoginPassportConf jwtConf;

  @Autowired
  private LoginPassportService loginPassportService;

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void test() throws UserException {
    Long accountId = 2L;
    String token = loginPassportService.createToken(accountId);
    Long verifyId = loginPassportService.verifyToken(token);
    assertEquals(accountId, verifyId);
  }

  @Test
  public void testExpires() throws UserException {
    Algorithm algorithm = Algorithm.HMAC256(jwtConf.getSecret().getBytes());
    Date expiresAt = DateUtil.getDateAfterSeconds(new Date(), -10);
    String token = JWT.create().withSubject(jwtConf.getSubject()).withClaim("accountId", 2).withExpiresAt(expiresAt).sign(algorithm);

    thrown.expect(UserException.class);
    thrown.expectMessage(ErrorMessage.TOKEN_EXPIRED.getMsg());
    loginPassportService.verifyToken(token);
  }

  @Test
  public void testSubjectVerify() throws UserException {
    Algorithm algorithm = Algorithm.HMAC256(jwtConf.getSecret().getBytes());
    Date expiresAt = DateUtil.getDateAfterSeconds(new Date(), 10);
    String token = JWT.create().withSubject("WRONG_SUBJECT").withClaim("accountId", 2).withExpiresAt(expiresAt).sign(algorithm);

    thrown.expect(UserException.class);
    thrown.expectMessage(ErrorMessage.UNEXPECTED_TOKEN.getMsg());
    loginPassportService.verifyToken(token);
  }

  @Test
  public void testSecretVerify() throws UserException {
    Algorithm algorithm = Algorithm.HMAC256("WRONG_SECRET".getBytes());
    Date expiresAt = DateUtil.getDateAfterSeconds(new Date(), 10);
    String token = JWT.create().withSubject(jwtConf.getSubject()).withClaim("accountId", 2).withExpiresAt(expiresAt).sign(algorithm);

    thrown.expect(UserException.class);
    thrown.expectMessage(ErrorMessage.UNEXPECTED_TOKEN.getMsg());
    loginPassportService.verifyToken(token);
  }

}
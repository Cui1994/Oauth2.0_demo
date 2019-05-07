package com.cui.demo.domain.model.passport;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cui.demo.exc.ErrorMessage;
import com.cui.demo.exc.UserException;
import com.cui.demo.utils.DateUtil;
import java.util.Date;
import org.springframework.stereotype.Service;

/**
 * 登陆鉴权 Service，负责登录 token 相关动作，包括生成、缓存、鉴权等。
 * @author tao
 */
@Service
public class LoginPassportService {

  /**
   * jwt 相关
   */
  private final static String SECRET = "DEMO_JWT_SECRET";
  private final static String SUBJECT = "login";
  private Algorithm algorithm = Algorithm.HMAC256(SECRET.getBytes());

  /**
   * token 失效时间，default: 3600s
   */
  private final static Integer TOKEN_EXPIRED_SECONDS = 3600;

  public String createToken(Long accountId) {
    Date expiresAt = DateUtil.getDateAfterSeconds(new Date(), TOKEN_EXPIRED_SECONDS);
    return JWT.create().withSubject(SUBJECT).withClaim("accountId", accountId).withExpiresAt(expiresAt).sign(algorithm);
  }

  public Long verifyToken(String token) throws UserException {
    JWTVerifier verifier = JWT.require(algorithm).build();

    DecodedJWT jwt;
    try {
      jwt = verifier.verify(token);
    } catch (TokenExpiredException e) {
      throw ErrorMessage.TOKEN_CHECK_FAIL.createUserExc("token 已过期");
    } catch (Exception e) {
      throw ErrorMessage.TOKEN_CHECK_FAIL.createUserExc();
    }

    if (!jwt.getSubject().equals(SUBJECT)) {
      throw ErrorMessage.TOKEN_CHECK_FAIL.createUserExc("非法 token");
    }

    return jwt.getClaim("accountId").asLong();
  }
}

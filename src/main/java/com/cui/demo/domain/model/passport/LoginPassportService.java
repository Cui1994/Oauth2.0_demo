package com.cui.demo.domain.model.passport;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cui.demo.conf.JWTLoginPassportConf;
import com.cui.demo.exc.ErrorMessage;
import com.cui.demo.exc.UserException;
import com.cui.demo.utils.DateUtil;
import com.cui.demo.utils.ExceptionUtil;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 登陆鉴权 Service，负责登录 token 相关动作，包括生成、缓存、鉴权等。
 * todo: token 主动失效，目前考虑 redis
 * @author tao
 */
@Service
public class LoginPassportService {

  private final static Logger logger = LoggerFactory.getLogger(LoginPassportService);

  private final JWTLoginPassportConf conf;

  LoginPassportService(JWTLoginPassportConf conf) {
    this.conf = conf;
  }

  public String createToken(Long accountId) {
    Algorithm algorithm = Algorithm.HMAC256(conf.getSecret().getBytes());
    Date expiresAt = DateUtil.getDateAfterSeconds(new Date(), conf.getExpiredSeconds());
    return JWT.create().withSubject(conf.getSubject()).withClaim("accountId", accountId).withExpiresAt(expiresAt).sign(algorithm);
  }

  public Long verifyToken(String token) throws UserException {
    Algorithm algorithm = Algorithm.HMAC256(conf.getSecret().getBytes());
    JWTVerifier verifier = JWT.require(algorithm).withSubject(conf.getSubject()).build();

    try {
      DecodedJWT jwt = verifier.verify(token);
      return jwt.getClaim("accountId").asLong();
    }
    catch (TokenExpiredException e) {
      throw ErrorMessage.TOKEN_EXPIRED.createUserExc();
    } catch (JWTVerificationException e) {
      logger.error(String.format("非法 token 记录，token: %s\n error: %s", token, ExceptionUtil.getStackTrace(e)));
      throw ErrorMessage.UNEXPECTED_TOKEN.createUserExc();
    } catch (Exception e) {
      logger.error(String.format("token校验出错, token: %s\n, error: %s", token, ExceptionUtil.getStackTrace(e)));
      throw ErrorMessage.TOKEN_CHECK_FAIL.createUserExc();
    }
  }
}

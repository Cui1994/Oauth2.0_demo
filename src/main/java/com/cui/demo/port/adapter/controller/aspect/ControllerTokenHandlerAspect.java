package com.cui.demo.port.adapter.controller.aspect;

import com.cui.demo.domain.model.passport.LoginPassportService;
import com.cui.demo.exc.ErrorMessage;
import com.cui.demo.port.adapter.controller.dto.AccountVerifyInfo;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Token 鉴权 Aspect
 * @author tao
 */
@Aspect
@Component
public class ControllerTokenHandlerAspect {
  
  @Autowired
  LoginPassportService loginPassportService;

  @Pointcut("@annotation(com.cui.demo.port.adapter.controller.annotation.TokenHandler)")
  private void checkToken() {
  }

  /**
   * 从 http header 中获取 token，调用 passportService 进行鉴权并将鉴权结果（accountId）set 进 AccountVerifyInfo
   * @param joinPoint
   * @return
   * @throws Throwable
   */
  @Around("checkToken()")
  public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
        .getRequestAttributes()).getRequest();

    String token = request.getHeader("HTTP-ACCESS-TOKEN");
    if (token == null) {
      throw ErrorMessage.TOKEN_CHECK_FAIL.createUserExc();
    }

    Long accountId = loginPassportService.verifyToken(token);

    Object[] args = joinPoint.getArgs();
    for (Object arg : args) {
      if (arg instanceof AccountVerifyInfo) {
        ((AccountVerifyInfo) arg).setAccountId(accountId);
      }
    }

    return joinPoint.proceed();
  }

}

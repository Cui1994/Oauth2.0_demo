package com.cui.demo.conf;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
@Data
public class JWTLoginPassportConf {

  @Value("${jwt.login.secret}")
  private String secret;

  @Value("${jwt.login.subject}")
  private String subject;

  @Value("${jwt.login.expiredSeconds}")
  private Integer expiredSeconds;

}

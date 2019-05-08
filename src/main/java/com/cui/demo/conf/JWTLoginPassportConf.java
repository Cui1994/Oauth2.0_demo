package com.cui.demo.conf;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "jwt.login", ignoreInvalidFields = false)
@Component
@Data
public class JWTLoginPassportConf {

  private String secret;
  private String subject;
  private Integer expiredSeconds;

}

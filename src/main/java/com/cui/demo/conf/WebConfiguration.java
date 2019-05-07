package com.cui.demo.conf;

import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 此处参照 blog:https://jpanj.com/2018/SpringBoot-%E4%B8%AD%E7%BB%9F%E4%B8%80%E5%8C%85%E8%A3%85%E5%93%8D%E5%BA%94/ 解决统一响应中字符串类型转换问题
 * @author tao
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.add(0, new MappingJackson2HttpMessageConverter());
  }
}
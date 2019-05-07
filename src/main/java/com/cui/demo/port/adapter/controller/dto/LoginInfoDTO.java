package com.cui.demo.port.adapter.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginInfoDTO {

  private Long accountId;
  private String name;
  private String mobile;
  private String token;

}

package com.cui.demo.port.adapter.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

  private Long id;
  private String name;
  private String mobile;
}

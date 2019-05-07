package com.cui.demo.port.adapter.persistence.tb;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TbAccount {
    private Long id;

    private String name;

    private String mobile;

    private String password;

    private String salt;

    private Date createdAt;

    private Date updatedAt;

    private Integer isDeleted;
}
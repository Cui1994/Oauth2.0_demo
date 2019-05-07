package com.cui.demo.port.adapter.persistence.tb;

public interface TbAccountMapper {
    int deleteByPrimaryKey(Long id);

    long insert(TbAccount record);

    int insertSelective(TbAccount record);

    TbAccount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbAccount record);

    int updateByPrimaryKey(TbAccount record);

    TbAccount selectByMobile(String mobile);
}
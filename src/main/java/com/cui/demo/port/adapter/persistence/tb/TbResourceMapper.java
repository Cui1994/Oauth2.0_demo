package com.cui.demo.port.adapter.persistence.tb;

public interface TbResourceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbResource record);

    int insertSelective(TbResource record);

    TbResource selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbResource record);

    int updateByPrimaryKey(TbResource record);
}
package com.cui.demo.service.impl;

import com.cui.demo.dao.ResourceMapper;
import com.cui.demo.domain.po.Resource;
import com.cui.demo.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceServiceImpl implements ResourceService {

  @Autowired
  private ResourceMapper resourceMapper;

  @Override
  public Resource getResourceById(Long id) {
    return resourceMapper.selectByPrimaryKey(id);
  }
}

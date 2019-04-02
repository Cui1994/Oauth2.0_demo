package com.cui.demo.controller;

import com.cui.demo.domain.po.Resource;
import com.cui.demo.service.ResourceService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author cui1994
 */
@Service
@RestController
public class ResourceController {

  @Autowired
  private ResourceService resourceService;

  @RequestMapping("/")
  public String index() {
    return "Hi";
  }

  @GetMapping("/resource")
  public Resource getResourceById(HttpServletRequest request) {
    Long id = Long.valueOf(request.getParameter("id"));

    return resourceService.getResourceById(id);
  }

}

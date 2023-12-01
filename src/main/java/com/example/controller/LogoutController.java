package com.example.controller;

import com.example.entity.RestBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class LogoutController {
  @PostMapping("/logout")
  public String logout(){
    SecurityContextHolder.clearContext();
    return RestBean.success("退出登录成功").asJsonString();
  }
  @GetMapping("/test")
  public String test(){
    return RestBean.success("ddrt").asJsonString();
  }

}

package com.example.controller;

import com.example.entity.RestBean;
import com.example.entity.dto.Account;
import com.example.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth/user")
public class AccountController {
  @Autowired
  AccountService accountService;

  @GetMapping("/list")
  String getUserList() {
    List<Map<String, Object>> userList = accountService.getUserList();
    return RestBean.success(userList).asJsonString();
  }

  @PostMapping("/add")
  String addUserList(@Valid @RequestBody Account account, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      IllegalArgumentException illegalArgumentException = new IllegalArgumentException(bindingResult.getAllErrors().get(0).getDefaultMessage());
      return RestBean.failure(String.valueOf(illegalArgumentException)).asJsonString();
    }
    String s = accountService.addUserList(account);
    if (s != null) {
      return RestBean.success(null).asJsonString();
    }
    return RestBean.failure("用户名已经存在").asJsonString();
  }

  @PostMapping("/delete")
  String deleteById(@RequestParam("id") Integer id){
    return accountService.deleteUser(id);
  }

  @PostMapping("/update")
  String update(@Valid @RequestBody Account account, BindingResult bindingResult){
    if (bindingResult.hasErrors()) {
      IllegalArgumentException illegalArgumentException = new IllegalArgumentException(bindingResult.getAllErrors().get(0).getDefaultMessage());
      return RestBean.failure(String.valueOf(illegalArgumentException)).asJsonString();
    }
    accountService.updateUser(account);
    return RestBean.success(null).asJsonString();
  }
}

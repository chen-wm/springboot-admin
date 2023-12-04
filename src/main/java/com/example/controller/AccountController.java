package com.example.controller;

import com.example.entity.RestBean;
import com.example.entity.dto.Account;
import com.example.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
  String addUserList(@Valid @RequestParam Account account) {
    System.out.println("5555"+account);
    String accountList = accountService.addUserList(account);
    return RestBean.success(accountList).asJsonString();
  }
}

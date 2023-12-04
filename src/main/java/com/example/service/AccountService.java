package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.dto.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;

public interface AccountService extends IService<Account>, UserDetailsService {

  Account findAccountByNameOrEmail(String text);
  List<Map<String, Object>> getUserList();

  String addUserList(Account account);

  String deleteUserList();
  String editUserList();

  Map<String, Object> getById(int id);
}

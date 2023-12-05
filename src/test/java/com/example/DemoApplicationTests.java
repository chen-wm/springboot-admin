package com.example;

import com.example.entity.dto.Account;
import com.example.mapper.AccountMapper;
import com.example.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.example.utils.Const.DEFAULT_DATETIME_FORMAT;

@SpringBootTest
@RestController
class DemoApplicationTests {
  @Autowired
  AccountMapper accountMapper;
  @Autowired
  AccountService accountService;
  @Test
  void contextLoads() {
    BCryptPasswordEncoder BCryptPasswordEncoder = new BCryptPasswordEncoder();
    System.out.println(BCryptPasswordEncoder.encode("123456"));
  }


  @Test
  void testGetAll() {
    List<Account> accountList = accountMapper.selectList();
    System.out.println(accountList);
  }

  @Test
  void testDelete() {
    accountMapper.deleteById(14);
    List<Account> accountList = accountMapper.selectList();
    System.out.println(accountList);
  }

  @Test
  void testUpdate() {
    Account account = new Account();
    account.setId(8);
    account.setUsername("cwm");
    Date date = new Date();
    String simpleDateFormat = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT).format(date);
    account.setRegisterTime(simpleDateFormat);
    accountMapper.updateById(account);
  }

  @Test
  void testById() {
    System.out.println(accountMapper.selectById(8));
    Account account = new Account();
    account.setUsername("cwm");
    account.setRole("test");

  }

}

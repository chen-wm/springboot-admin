package com.example;

import com.example.entity.dto.Account;
import com.example.mapper.AccountMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
@RestController
class DemoApplicationTests {
  @Autowired
  AccountMapper accountMapper;

  @Test
  void contextLoads() {
    BCryptPasswordEncoder BCryptPasswordEncoder = new BCryptPasswordEncoder();
    System.out.println(new BCryptPasswordEncoder().encode("123456"));
  }


  @Test
  void testGetAll(){
    List<Account> accountList = accountMapper.selectList();
    System.out.println(accountList);
  }

  @Test
  void add(){
    List<Account> list = accountMapper.selectList();

    Date date = new Date( );
    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    Account newAccount = new Account();
    newAccount.setEmail("3212@qq.com");
    newAccount.setRole("admin");
    newAccount.setUsername("test");
    newAccount.setRegisterTime("ewqeqw");
    newAccount.setRegisterTime(ft.format(date));
    newAccount.setId(0);
    accountMapper.insert(newAccount);
    System.out.println(accountMapper.selectList());
  }
}

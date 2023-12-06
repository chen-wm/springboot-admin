package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.RestBean;
import com.example.entity.dto.Account;
import com.example.mapper.AccountMapper;
import com.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.example.utils.Const.DEFAULT_DATETIME_FORMAT;

@Service
@RestController
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {
  @Autowired
  private AccountMapper accountMapper;

  /**
   * 从数据库中通过用户名或邮箱查找用户详细信息
   *
   * @param username 用户名
   * @return 用户详细信息
   * @throws UsernameNotFoundException 如果用户未找到则抛出此异常
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Account account = this.findAccountByNameOrEmail(username);
    System.out.println(account);
    if (account == null) {
      throw new UsernameNotFoundException("用户名或密码错误");
    }
    return User.withUsername(username).password(account.getPassword()).roles(account.getRole()).build();
  }

  @Override
  public Account findAccountByNameOrEmail(String text) {
    return this.query().eq("username", text).or()
        .eq("email", text).one();
  }

  @Override
  public List<Map<String, Object>> getUserList() {
    List<Account> list = accountMapper.selectList();
    List<Map<String, Object>> userList = new ArrayList<>();
    for (Account account : list) {
      Map<String, Object> userMap = new HashMap<>();
      userMap.put("email", account.getEmail());
      userMap.put("id", account.getId());
      userMap.put("registerTime", account.getRegisterTime());
      userMap.put("username", account.getUsername());
      userMap.put("role", account.getRole());
      userList.add(userMap);
    }
    return userList;
  }

  @Override
  public String addUserList(Account account) {
    String username = account.getUsername();
    List<Account> list = accountMapper.selectList();
    for (Account item : list) {
      if (username.equals(item.getUsername())) return null;
    }
    Date date = new Date();
    String ft = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT).format(date);
    Account newAccount = new Account();
    newAccount.setEmail(account.getEmail());
    BCryptPasswordEncoder BCryptPasswordEncoder = new BCryptPasswordEncoder();
    String encodePwd = BCryptPasswordEncoder.encode(account.getPassword());
    newAccount.setPassword(encodePwd);
    newAccount.setRole(account.getRole());
    newAccount.setUsername(username);
    newAccount.setRegisterTime(ft);
    newAccount.setId(0);
    accountMapper.insert(newAccount);
    return list.toString();
  }

  @Override
  public String deleteUser(Integer id) {
    if(id == null) {
      return RestBean.failure("id不能为空").asJsonString();
    }
    accountMapper.deleteById(id);
    return RestBean.success(null).asJsonString();
  }

  @Override
  public void updateUser(Account account) {
    Account account1 = new Account();
    account1.setRole(account.getRole());
    account1.setUsername(account.getUsername());
    account1.setEmail(account.getEmail());
    BCryptPasswordEncoder BCryptPasswordEncoder = new BCryptPasswordEncoder();
    String encodePwd = BCryptPasswordEncoder.encode(account.getPassword());
    account1.setPassword(encodePwd);
    account1.setId(account.getId());
    accountMapper.updateById(account1);
  }
}

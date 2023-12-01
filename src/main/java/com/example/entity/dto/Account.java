package com.example.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.entity.BaseData;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("db_account")
@AllArgsConstructor
@NoArgsConstructor
public class Account implements BaseData {
  @TableId(type = IdType.AUTO)
  Integer id;
  @NotBlank(message = "请输入用户名")
  String username;
  @NotBlank(message = "密码")
  String password;
  @NotBlank(message = "请输入邮箱")
  @Email(message = "邮箱格式不正确，请检查后在输入")
  String email;
  String role;
  String registerTime;
}

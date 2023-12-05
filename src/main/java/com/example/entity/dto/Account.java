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
  @NotBlank(message = "用户名不能为空")
  String username;
  @NotBlank(message = "密码不能为空")
  String password;
  @NotBlank(message = "邮箱不能为空")
  @Email(message = "邮箱格式不正确，请检查后在输入")
  String email;
  String role;
  String registerTime;
}

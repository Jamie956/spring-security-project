package com.jamie.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("acl_user")
public class Users {
    private Integer id;
    private String username;
    private String password;
}

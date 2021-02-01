package com.jamie.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 数据库用户
 */
@Data
@TableName("acl_user")
public class AclUser {
    private Integer id;
    private String username;
    private String password;
}

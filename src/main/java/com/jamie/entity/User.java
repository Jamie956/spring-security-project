package com.jamie.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 请求对象的user
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String nickName;
    private String salt;
    private String token;
}

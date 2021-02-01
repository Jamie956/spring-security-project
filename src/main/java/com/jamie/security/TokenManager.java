package com.jamie.security;

import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenManager {
    /**
     * token有效时长
     */
    private final static long TOKEN_EXPIRATION = 24 * 60 * 60 * 1000;
    /**
     * 编码秘钥
     */
    private final static String TOKEN_SIGN_KEY = "123456";

    /**
     * 根据用户名获取token
     * @param username
     * @return
     */
    public String createToken(String username) {
        return Jwts.builder().setSubject(username).setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION)).signWith(SignatureAlgorithm.HS512, TOKEN_SIGN_KEY).compressWith(CompressionCodecs.GZIP).compact();
    }

    /**
     * 根据token 获取用户信息
     * @param token
     * @return
     */
    public String getUser(String token) {
        if(token.length() == 0) {
            throw new RuntimeException("token 不能为空");
        }
        try {
            return Jwts.parser().setSigningKey(TOKEN_SIGN_KEY).parseClaimsJws(token).getBody().getSubject();
        }catch (ExpiredJwtException e) {
            throw new RuntimeException("你的token过期了哦");
        }
    }
}

package com.jamie.security;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenLogoutHandler implements LogoutHandler {

    private TokenManager tokenManager;
    private RedisTemplate redisTemplate;

    public TokenLogoutHandler(TokenManager tokenManager,RedisTemplate redisTemplate) {
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
    }
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        String token = request.getHeader("token");
        if(token != null) {
            String userName = tokenManager.getUserByToken(token);
            //redis 根据key=userName删除缓存

            redisTemplate.delete(userName);
        }
        try {
            response.getWriter().println("logout!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

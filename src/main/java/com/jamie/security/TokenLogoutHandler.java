package com.jamie.security;

import com.jamie.utils.ResponseUtil;
import com.jamie.utils.Result;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 退出登录
 */
public class TokenLogoutHandler implements LogoutHandler {
    private TokenManager tokenManager;
    private RedisTemplate redisTemplate;

    public TokenLogoutHandler(TokenManager tokenManager,RedisTemplate redisTemplate) {
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 根据token 获取用户信息，删除该用户的缓存
     * @param request
     * @param response
     * @param authentication
     */
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String token = request.getHeader("token");
        if(token != null) {
            String userName = tokenManager.getUser(token);
            //redis 根据key=userName删除缓存
            redisTemplate.delete(userName);
        }
        ResponseUtil.out(response, Result.ok().message("退出成功"));
    }
}

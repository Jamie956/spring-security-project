package com.jamie.config;

import com.jamie.filter.TokenAuthFilter;
import com.jamie.filter.TokenLoginFilter;
import com.jamie.security.MD5PasswordEncoder;
import com.jamie.security.TokenLogoutHandler;
import com.jamie.security.TokenManager;
import com.jamie.security.UnauthEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private MD5PasswordEncoder md5PasswordEncoder;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //自定义登录页面
                .formLogin()
                //登录页面设置
//                .loginPage("")
                //登录访问路径
//                .loginProcessingUrl("/user/login")
                //登录成功后跳转路径
//                .defaultSuccessUrl("/hi").permitAll()
                .and().exceptionHandling()
                //没有权限访问返回
                .authenticationEntryPoint(new UnauthEntryPoint())
                //关闭csrf 防护
                .and().csrf().disable()
                //认证配置
                .authorizeRequests()
                //指定URL 直接访问，无需验证
                .antMatchers("/list").permitAll()
                //其他请求 需要身份验证
                .anyRequest().authenticated()
                //退出路径
                .and().logout().logoutUrl("/logout")
                .addLogoutHandler(new TokenLogoutHandler(tokenManager,redisTemplate)).and()
                .addFilter(new TokenLoginFilter(authenticationManager(), tokenManager, redisTemplate))
                .addFilter(new TokenAuthFilter(authenticationManager(), tokenManager, redisTemplate)).httpBasic();
    }

    /**
     * 设置密码加密方式
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(md5PasswordEncoder);
    }

    /**
     * 不进行认证的路径，可以直接访问
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/api/**");
    }
}

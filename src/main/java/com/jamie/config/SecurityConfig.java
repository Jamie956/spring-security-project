package com.jamie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //注入密码加密方式
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //退出
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/list").permitAll();

        //表单登录
        http
                //自定义登录页面
                .formLogin()
                //登录页面设置
//                .loginPage("/login.html")
                //登录访问路径
//                .loginProcessingUrl("/user/login")
                //登录成功后跳转路径
                .defaultSuccessUrl("/hi").permitAll()
                //认证配置
                .and().authorizeRequests()
                //指定URL 直接访问，无需验证
                .antMatchers("/list").permitAll()
                //其他请求 需要身份验证
                .anyRequest().authenticated()
                //关闭csrf 防护
                .and().csrf().disable();
    }
}

# spring-security-project
principal：用户
authentication：登录
authorization：用户权限

默认username: user

关键源码
```
FilterSecurityInterceptor.invoke：认证过滤链
UsernamePasswordAuthenticationFilter：对/login 的 POST 请求做拦截，校验表单中用户名，密码
UserDetailsService：重写，加载数据库用户
UserDetails：用户主体
package org.springframework.security.core.userdetails.User：UserDetails的实现
package org.springframework.security.crypto.password.PasswordEncoder：密码加密，密码比对
```


package com.jamie.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jamie.entity.Users;
import com.jamie.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class LoginService implements UserDetailsService {

    @Autowired
    private UsersMapper usersMapper;

    /**
     * 数据库查询用户、密码、权限
     * @param username 用户名
     * @return 用户
     * @throws UsernameNotFoundException 找不到用户名
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users users = usersMapper.selectOne(new QueryWrapper<Users>().eq("username", username));
        if(users == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        return new User(users.getUsername(), users.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList("role"));
    }
}

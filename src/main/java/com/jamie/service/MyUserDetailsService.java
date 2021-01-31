package com.jamie.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jamie.entity.Permission;
import com.jamie.entity.SecurityUser;
import com.jamie.entity.User;
import com.jamie.entity.Users;
import com.jamie.mapper.PermissionMapper;
import com.jamie.mapper.UsersMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 数据库查询用户、密码、权限
     *
     * @param username 用户名
     * @return 用户
     * @throws UsernameNotFoundException 找不到用户名
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users dbUsers = usersMapper.selectOne(new QueryWrapper<Users>().eq("username", username));
        if (dbUsers == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        User curUser = new User();
        BeanUtils.copyProperties(dbUsers, curUser);

        SecurityUser securityUser = new SecurityUser();
        securityUser.setCurrentUserInfo(curUser);
        List<Permission> permissionsList = permissionMapper.selectList(new QueryWrapper<Permission>().eq("type", "2"));
        securityUser.setPermissionValueList(permissionsList.stream().map(Permission::getPermissionValue).collect(Collectors.toList()));

        return securityUser;
    }
}

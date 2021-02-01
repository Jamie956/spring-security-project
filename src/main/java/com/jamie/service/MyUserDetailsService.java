package com.jamie.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jamie.entity.AclUser;
import com.jamie.entity.Permission;
import com.jamie.entity.SecurityUser;
import com.jamie.entity.User;
import com.jamie.mapper.AclUserMapper;
import com.jamie.mapper.PermissionMapper;
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
    private AclUserMapper aclUserMapper;

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
        //查询数据库用户
        AclUser aclUser = aclUserMapper.selectOne(new QueryWrapper<AclUser>().eq("username", username));
        if (aclUser == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        //将数据库用户复制到请求用户对象
        User user = new User();
        BeanUtils.copyProperties(aclUser, user);

        //security 用户
//        securityUser.setCurrentUserInfo(user);
        //查询用户拥有的全部权限
        List<Permission> permissionsList = permissionMapper.selectList(new QueryWrapper<Permission>().eq("type", "2"));
        List<String> valueList = permissionsList.stream().map(Permission::getPermissionValue).collect(Collectors.toList());
//        securityUser.setPermissionValueList(permissionsList.stream().map(Permission::getPermissionValue).collect(Collectors.toList()));
        return new SecurityUser(user, valueList);
    }
}

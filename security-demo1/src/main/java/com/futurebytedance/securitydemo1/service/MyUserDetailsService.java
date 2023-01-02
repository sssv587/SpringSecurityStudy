package com.futurebytedance.securitydemo1.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.futurebytedance.securitydemo1.entity.Users;
import com.futurebytedance.securitydemo1.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yuhang.sun
 * @version 1.0
 * @date 2023/1/1 - 17:46
 * @Description
 */
@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UsersMapper usersMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //调用userMapper方法查询数据库
        QueryWrapper<Users> wrapper = new QueryWrapper<>();
        //where username = ?
        wrapper.eq("username", username);
        Users users = usersMapper.selectOne(wrapper);
        //判断
        if (users == null) { //数据库没有用户名，认证失败
            throw new UsernameNotFoundException("用户名不存在!!!");
        }

        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("admins,ROLE_sale");
        //从查询数据库返回users对象,得到用户名和密码,返回
        return new User(users.getUsername(), passwordEncoder.encode(users.getPassword()), auths);
    }
}

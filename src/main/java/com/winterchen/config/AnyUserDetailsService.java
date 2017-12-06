package com.winterchen.config;

import com.winterchen.domain.UserEntity;
import com.winterchen.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * spring security 数据库验证类
 * Created by Administrator on 2017/11/29.
 */
@Service
public class AnyUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    AnyUserDetailsService(UserService userService){
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userService.findUserByName(username);
        if (userEntity == null){
            throw new UsernameNotFoundException("用户不存在");
        }

        List<SimpleGrantedAuthority> simpleGrantedAuthorities = createAuthorities(userEntity.getRoles());

        return new User(userEntity.getUserName(),userEntity.getPassword(),simpleGrantedAuthorities);

    }

    /**
     * 权限字符串转化
     *
     * 如 "USER,ADMIN" -> SimpleGrantedAuthority("USER") + SimpleGrantedAuthority("ADMIN")
     *
     * @param roleStr 权限字符串
     */
    private List<SimpleGrantedAuthority> createAuthorities(String roleStr){
        String[] roles = roleStr.split(",");
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        for (String role : roles) {
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role));
        }
        return simpleGrantedAuthorities;
    }
}

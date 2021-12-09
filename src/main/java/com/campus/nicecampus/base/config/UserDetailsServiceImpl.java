package com.campus.nicecampus.base.config;

import com.campus.nicecampus.base.mapper.UserMapper;
import com.campus.nicecampus.base.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> userList = userMapper.selectByMap(Map.of("username", username));
        if(userList == null || userList.size() == 0){
            throw new UsernameNotFoundException("用户名不存在");
        }
        User user = userList.get(0);
        SimpleGrantedAuthority role = new SimpleGrantedAuthority("common");
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getUsername(),user.getPassword(),List.of(role));
        return userDetails;
    }

}

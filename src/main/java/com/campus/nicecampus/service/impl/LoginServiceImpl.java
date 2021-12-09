package com.campus.nicecampus.service.impl;

import com.campus.nicecampus.base.mapper.UserMapper;
import com.campus.nicecampus.base.model.User;
import com.campus.nicecampus.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    UserMapper userMapper;
    @Override
    public boolean doLogin(String username, String pwd) throws Exception {
        List<User> userList = userMapper.selectByMap(Map.of("username", username));
        if(userList == null || userList.size() == 0){
            throw new Exception("用户名不存在");
        }
        User user = userList.get(0);
        if(pwd!= null && pwd.equals(user.getPassword())){
            return true;
        }else {
            throw new Exception("密码错误");
        }
    }
}

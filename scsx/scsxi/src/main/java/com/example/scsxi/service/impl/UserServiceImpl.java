package com.example.scsxi.service.impl;

import com.example.scsxi.mapper.UserMapper;
import com.example.scsxi.pojo.User;
import com.example.scsxi.service.UserService;
import com.example.scsxi.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public void register(String username, String password) {
        //加密
        String Md5String = Md5Util.getMD5(password);
            userMapper.add(username, Md5String);
    }
    @Override
    public void login(String username, String password) {


    }
}

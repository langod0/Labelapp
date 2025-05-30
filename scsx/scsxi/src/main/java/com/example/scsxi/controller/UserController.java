package com.example.scsxi.controller;

import com.example.scsxi.pojo.Result;
import com.example.scsxi.pojo.User;
import com.example.scsxi.service.UserService;
import com.example.scsxi.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public Result register(String username, String password){
//        查询用户名是否已存在
        if(userService.findByUsername(username) == null) {
//              注册
            userService.register(username, password);
            return Result.success("注册成功");
        }else {
            return Result.fail("用户名已存在");
        }
    }

    @PostMapping("/login")
    public Result login(String username, String password){
//        账户或者密码错误
        User user = userService.findByUsername(username);
        if(user == null || !Md5Util.getMD5(password).equals(user.getPassword())) {
            return Result.fail("用户名或密码错误");
        }else {
//            userService.login(username, password);
            return Result.success("登录成功");
        }

    }
}

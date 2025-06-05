package com.example.scsxi.controller;

import com.example.scsxi.pojo.Result;
import com.example.scsxi.pojo.User;
import com.example.scsxi.service.CodeService;
import com.example.scsxi.service.UserService;
import com.example.scsxi.utils.JwtUtil;
import com.example.scsxi.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CodeService codeService;


    // 注册
    @PostMapping("/register")
    public Result register(@RequestBody Map<String, String> body){
        int op=Integer.parseInt(body.get("op"));
        String email=body.get("email");

        switch (op){
            case 0:
                if(email==null){
                    return Result.fail("邮箱不能为空");
                }
                if(!codeService.sendCode(email)){
                     return Result.fail("验证码发送失败");
                }
                return Result.success("验证码发送成功");
            case 1:
                String code= body.get("code");
                String password=body.get("password");
                String agpassword=body.get("agpassword");
                if(email==null||code==null||password==null||agpassword==null){
                     return Result.fail("请填写完整");
                }
                if(!password.equals(agpassword)){
                     return Result.fail("密码不一致");
                }
                 if(!codeService.checkCode(email,code)){
                     return Result.fail("验证码错误");
                }
                 if(userService.findByEmail(email)!=null){
                     return Result.fail("邮箱已被注册");
                 }
                 userService .register(email,password,email);
                 return Result.success("注册成功");
            default:
                 return Result.fail("请选择操作");
        }

    }



    // 登录
    @PostMapping("/login")
    public Result<String> login(@RequestBody Map<String, String>body){
//        邮箱或者密码错误
        String email=body.get("email");
        String password=body.get("password");
        String job=body.get("identity");
        User user = userService.findByEmail(email);
        if(user == null || !Md5Util.getMD5(password).equals(user.getPassword())|| !job.equals(String.valueOf(user.getJob()))) {
            return Result.fail("用户名或密码错误");
        }else {
//            生成token令牌并登录
            Map<String, Object> claims = new HashMap<>();
            claims.put("email", user.getEmail());
            claims.put("job", job);
            String token=JwtUtil.genToken(claims);
            return Result.success(token);
        }

    }

    @PostMapping("/find")
     public Result<String> find(@RequestBody Map<String, String>body){
        int op=Integer.parseInt(body.get("op"));
        String email=body.get("email");
        switch (op){
            case 0:
                if(email==null){
                     return Result.fail("邮箱不能为空");
                }
                 if(!codeService.sendCode(email)){
                     return Result.fail("验证码发送失败");
                }
                 return Result.success("验证码发送成功");
            case  1:
                 String code= body.get("code");
                 String password=body.get("password");

                  if(email==null||code==null||password==null){
                     return Result.fail("请填写完整");
                 }
                  if(!codeService.checkCode(email,code)){
                     return Result.fail("验证码错误");
                 }
                  userService.updatePassword(email,password);
                  return Result.success("修改成功");
            default:
                 return Result.fail("请选择操作");
        }
    }
}

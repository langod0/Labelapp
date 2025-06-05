package com.example.scsxi.controller;

import com.example.scsxi.pojo.Result;
import com.example.scsxi.service.CustomerService;
import com.example.scsxi.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
   @PostMapping("/upload")
    public Result<String> upload(@RequestParam("name")String name, @RequestParam("file")MultipartFile orgfile,
                                 @RequestHeader("Authorization") String token) {
       try {
           Map<String, Object> claims = JwtUtil.verify(token);
           String email = (String) claims.get("email");
           byte[] file = orgfile.getBytes();
           customerService.upload(name,file,email);
           return Result.success("上传成功");
       } catch (Exception e) {
           throw new RuntimeException(e);
       }

   }

   @GetMapping("/getdata")
    public Result<List<Map<String, Object>>> getdata(@RequestHeader("Authorization") String token) {
       System.out.println("0");
       if (token == null||token.isEmpty()){
           return Result.fail("缺少token");
       }
       try {
           Map<String, Object> claims = JwtUtil.verify(token);
           String email = (String) claims.get("email");
           if (email == null||email.isEmpty()){
               return Result.fail("invalid token");
           }
           List<Map<String, Object>> data = customerService.getdata(email);
           return Result.success(data);
       }catch (Exception e){
           return Result.fail("token解析失败或无效");
       }

   }

   @PostMapping("/checkresult")
    public Result<List<Map<String, Object>>> checkresult(@RequestBody Map<String,String> body,@RequestHeader("Authorization") String token) {
       String name=  body.get("name");
        try {
             Map<String, Object> claims = JwtUtil.verify(token);
             String email = (String) claims.get("email");
            List<Map<String, Object>> data = customerService.checkresult(name,email);
            return Result.success(data);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("token解析失败或无效");
        }

    }

    @PostMapping("/delete")
    public Result<String> delete(@RequestBody Map<String,String> body,@RequestHeader("Authorization") String token) {
        String name=  body.get("name");
        if (name==null){
            return Result.fail("缺少参数");
        }
        try {
            Map<String, Object> claims = JwtUtil.verify(token);
            String email = (String) claims.get("email");
            if (email == null||email.isEmpty()){
                return Result.fail("invalid token");
            }
            customerService.delete(name,email);
            return Result.success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("token解析失败或无效");
        }
    }


}

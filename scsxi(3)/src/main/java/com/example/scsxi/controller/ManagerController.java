package com.example.scsxi.controller;

import com.example.scsxi.pojo.Result;
import com.example.scsxi.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private ManagerService  managerService;
    @PostMapping("/delete")
    public Result<String> delete(@RequestBody Map<String,String> body){
        String name=  body.get("name");
        String email=  body.get("email");
        if (name==null||email==null) return Result.fail("缺少参数");
        try {
            managerService.delete(name,email);
            return Result.success("删除成功");
        }catch (Exception e){
            return Result.fail("删除失败");
        }
    }

    @GetMapping("/getdata")
    public Result<List<Map<String, Object>>> getdata(){
        try {
            List<Map<String, Object>> data = managerService.getdata();
            return Result.success(data);
        }catch (Exception e){
            return Result.fail("获取失败");
        }
    }

    @PostMapping("/selectworker")
    public Result<String> selectworker(@RequestBody Map<String,String> body) {
        String name=  body.get("name");
        String worker=  body.get("worker");
        String email = body.get("email");
        try {
            managerService.selectworker(name,worker,email);
            return Result.success("分配成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("分配失败");
        }
    }

    @GetMapping("/getworker")
    public Result<List<Map<String, Object>>> getworker(){
        try {
            List<Map<String, Object>> data = managerService.getworker();
            return Result.success(data);
        }catch (Exception e){
            return Result.fail("获取失败");
        }
    }

    @PostMapping("/updatedone")
    public Result<String> updatedone(@RequestBody Map<String,String> body) {
        String name=  body.get("name");
        String email = body.get("email");
        String done = body.get("done");
        try {
            managerService.updatedone(name,email,done);
            return Result.success("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("更新失败");
        }
    }

    @PostMapping("/checkresult")
    public Result<List<Map<String, Object>>> checkresult(@RequestBody Map<String,String> body){
        String name=  body.get("name");
        String email=  body.get("email");
        if (name==null||email==null) return Result.fail("缺少参数");
        try {
            List<Map<String, Object>> data = managerService.checkresult(name,email);
            return Result.success(data);
        }catch (Exception e){
            return Result.fail("获取失败");
        }
    }

    @PostMapping("/deleteworker")
    public Result<String> deleteworker(@RequestBody Map<String,String> body){
        String email=  body.get("email");
        if (email==null) return Result.fail("缺少参数");
        try {
            managerService.deleteworker(email);
            return Result.success("删除成功");
        }catch (Exception e){
            return Result.fail("删除失败");
        }
    }


}

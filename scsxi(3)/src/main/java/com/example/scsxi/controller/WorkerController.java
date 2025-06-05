package com.example.scsxi.controller;


import com.example.scsxi.pojo.Dataset;
import com.example.scsxi.pojo.Result;
import com.example.scsxi.service.WorkerService;
import com.example.scsxi.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/worker")
public class WorkerController {
    @Autowired
    private WorkerService workerService;

    @GetMapping("/getitem")
    public Result<List<Map<String,Object>>> getItem(@RequestHeader("Authorization") String token) {
        try {
            Map<String,Object> claims = JwtUtil.verify(token);
            String email=(String) claims.get("email");
            List<Map<String,Object>> data = workerService.getItem(email);
            return Result.success(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    @PostMapping("/checkresult")
    public Result<List<Map<String,Object>>> checkResult(@RequestBody Map<String,String> body) {
        try {
            String name = body.get("name");
            String email = body.get("email");
            if (name==null||email==null) return Result.fail("缺少参数");
            List<Map<String,Object>> data = workerService.checkResult(name, email);
            return Result.success(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @PostMapping("/delete")
    public Result<String> delete(@RequestBody Dataset dataset) {
        try {

            workerService.delete(
                    dataset.getName(), dataset.getEmail(),
                    dataset.getTime(), dataset.getSchool(), dataset.getProfession(),
                    dataset.getCourse(), dataset.getSemester(), dataset.getStuname(), dataset.getGrade()
            );return Result.success("删除成功");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @PostMapping("/update")
    public Result<String> update(@RequestBody Map<String,String> body) {
        try {
            String name = body.get("name");
            String email = body.get("email");
            String time =  body.get("time");
            String school =  body.get("school");
            String profession =  body.get("profession");
            String course =  body.get("course");
            String semester =  body.get("semester");
            String stuname =  body.get("stuname");
            String grade =  body.get("grade");
            workerService.update(  time, school, profession, course, semester, stuname, grade,name, email);
            String lasttime =  body.get("lasttime");
            String lastschool =  body.get("lastschool");
            String lastprofession =  body.get("lastprofession");
            String lastcourse =  body.get("lastcourse");
            String lastsemester =  body.get("lastsemester");
            String laststuname =  body.get("laststuname");
            String lastgrade =  body.get("lastgrade");
            workerService.delete( name, email,lasttime, lastschool, lastprofession, lastcourse, lastsemester, laststuname, lastgrade);
            return Result.success("更新成功");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("adddata")
    public Result<String> adddata(@RequestBody Dataset dataset) {
        try {
            workerService.adddata(
                    dataset.getTime(), dataset.getSchool(), dataset.getProfession(),
                    dataset.getCourse(), dataset.getSemester(), dataset.getStuname(), dataset.getGrade(),
                    dataset.getName(), dataset.getEmail()
            );
            return Result.success("添加成功");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("subdata")
    public Result<String> subdata(@RequestBody List<Dataset> datasets) {
        try {
            for (Dataset dataset : datasets) {
                workerService.adddata(
                        dataset.getTime(), dataset.getSchool(), dataset.getProfession(),
                        dataset.getCourse(), dataset.getSemester(), dataset.getStuname(), dataset.getGrade(),
                        dataset.getName(), dataset.getEmail()
                );
            }
            return Result.success("批量提交数据成功");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("subitem")
    public Result<String> subitem(@RequestBody Map<String,String> body) {
        try {
            String  name = body.get("name");
            String  email = body.get("email");
            workerService.updatePro(name,email);
            return Result.success("标注完成");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

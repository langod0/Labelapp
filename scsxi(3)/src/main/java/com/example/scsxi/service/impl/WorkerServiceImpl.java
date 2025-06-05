package com.example.scsxi.service.impl;

import com.example.scsxi.mapper.CustomerMapper;
import com.example.scsxi.mapper.WorkerMapper;
import com.example.scsxi.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    private WorkerMapper workerMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Override
    public List<Map<String, Object>> getItem(String email) {

        return workerMapper.getItem(email);
    }

    @Override
    public List<Map<String, Object>> checkResult(String name,  String email) {
        return customerMapper.checkresult(name,email);
    }
    @Override
    public void delete(String name, String email, String time, String school, String profession, String course, String semester, String stuname, String grade) {
        workerMapper.delete(name,email,time,school,profession,course,semester,stuname,grade);
    }
    @Override
    public void update( String lasttime, String lastschool, String lastprofession, String lastcourse, String lastsemester, String laststuname, String lastgrade,String name, String email) {
        workerMapper.adddata(lasttime,lastschool,lastprofession,lastcourse,lastsemester,laststuname,lastgrade,name,email);
    }
    @Override
    public void adddata(String time, String school, String profession, String course, String semester, String stuname, String grade, String name, String email) {
        workerMapper.adddata(time,school,profession,course,semester,stuname,grade,name,email);
    }
    @Override
    public void updatePro(String name, String email) {
        workerMapper.updatePro(name,email);
    }
}

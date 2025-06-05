package com.example.scsxi.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface WorkerService {
    List<Map<String, Object>> getItem(String email);

    List<Map<String, Object>> checkResult(String name, String email);

    void delete(String name, String email, String time, String school, String profession, String course, String semester, String stuname, String grade);

    void update( String lasttime, String lastschool, String lastprofession, String lastcourse, String lastsemester, String laststuname, String lastgrade,String name, String email);

    void adddata(String time, String school, String profession, String course, String semester, String stuname, String grade, String name, String email);

    void updatePro(String name, String email);
}

package com.example.scsxi.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ManagerService {
    void delete(String name, String email);

    List<Map<String, Object>> getdata();

    void selectworker(String name, String worker, String email);

    List<Map<String, Object>> getworker();

    void updatedone(String name, String email, String done);

    List<Map<String, Object>> checkresult(String name, String email);

    void deleteworker(String email);
}

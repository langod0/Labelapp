package com.example.scsxi.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface CustomerService {
    void upload(String name, byte[] file,String email);

    List<Map<String, Object>> getdata(String email);

    void delete(String name, String email);

    List<Map<String, Object>> checkresult(String name, String email);
}

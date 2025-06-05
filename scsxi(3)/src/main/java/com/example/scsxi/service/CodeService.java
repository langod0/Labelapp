package com.example.scsxi.service;


import org.springframework.stereotype.Service;

@Service
public interface CodeService {
    boolean sendCode(String email);

    boolean checkCode(String email, String code);
}

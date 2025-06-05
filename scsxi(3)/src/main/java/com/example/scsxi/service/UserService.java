package com.example.scsxi.service;

import com.example.scsxi.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User findByUsername(String username);

    void register(String username, String password,String email);

    void login(String username, String password);

    User findByEmail(String email);

    void updatePassword(String email, String password);
}

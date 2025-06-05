package com.example.scsxi.service.impl;

import com.example.scsxi.mapper.CustomerMapper;
import com.example.scsxi.mapper.ManagerMapper;
import com.example.scsxi.mapper.UserMapper;
import com.example.scsxi.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerMapper managerMapper;

    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public void delete(String name, String email) {
        customerMapper.deleteFromUpload(name,email);
        customerMapper.deleteFromDataset(name,email);
    }

    @Override
    public List<Map<String, Object>> getdata() {
        return managerMapper.getdata();
    }

    @Override
    public void selectworker(String name, String worker, String email) {
        managerMapper.updateWorker(name,worker,email);
    }

    @Override
    public List<Map<String, Object>> getworker() {
        return managerMapper.getworker();
    }

    @Override
    public void updatedone(String name, String email, String done) {
        managerMapper.updateDone(name,email,done);
    }

    @Override
    public List<Map<String, Object>> checkresult(String name, String email) {
        return customerMapper.checkresult(name,email);
    }

    @Override
    public void deleteworker(String email) {
        userMapper.delete(email);
    }

}

package com.example.scsxi.service.impl;

import com.example.scsxi.mapper.CustomerMapper;
import com.example.scsxi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;
    @Override
    public void upload(String name, byte[] file,String email) {
        customerMapper.upload(name,file,email);
    }
    @Override
    public List<Map<String, Object>> getdata(String email) {
        return customerMapper.getdata(email);
    }
    @Override
    public void delete(String name, String email) {
        customerMapper.deleteFromUpload(name,email);
        customerMapper.deleteFromDataset(name,email);
    }
    @Override
    public List<Map<String, Object>> checkresult(String name, String email) {
        return customerMapper.checkresult(name,email);
    }
}

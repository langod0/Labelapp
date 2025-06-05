package com.example.scsxi.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface ManagerMapper {

    @Select("SELECT * FROM upload")
    List<Map<String, Object>> getdata();
    @Update("UPDATE upload SET worker=#{worker} WHERE name=#{name} AND email=#{email}")
    void updateWorker(String name, String worker, String email);
    @Select("SELECT * FROM user WHERE job='1'")
    List<Map<String, Object>> getworker();
    @Update("UPDATE upload SET done = #{done} WHERE name = #{name} AND email = #{email}")
    void updateDone(String name, String email, String done);
}

package com.example.scsxi.mapper;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;
@Mapper
public interface WorkerMapper {
    @Select("SELECT u.* FROM upload u JOIN user us ON u.worker = us.username WHERE us.email = #{email}")
    List<Map<String, Object>> getItem(String email);

    @Delete("delete from dataset where name=#{name} and email=#{email} and time=#{time} and school=#{school} and profession=#{profession} and course=#{course} and semester=#{semester} and stuname=#{stuname} and grade=#{grade}")
    void delete(String name, String email, String time, String school, String profession, String course, String semester, String stuname, String grade);

    @Insert("INSERT INTO dataset (time, school, profession, course, semester, stuname, grade, name, email) " +
             "VALUES (#{time}, #{school}, #{profession}, #{course}, #{semester}, #{stuname}, #{grade}, #{name}, #{email})")
    void adddata(String time, String school, String profession, String course, String semester, String stuname, String grade, String name, String email);
    @Update("UPDATE upload set progress='1' where name=#{name} and email=#{email}")
    void updatePro(String name, String email);
}

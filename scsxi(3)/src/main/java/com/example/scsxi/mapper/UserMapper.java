package com.example.scsxi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.scsxi.pojo.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("select * from user where username=#{username}")
    User findByUsername(String username);
    @Insert("insert into user(username,email, password,create_time,update_time)"+
            "values(#{username},#{email},#{password},now(),now())")
    void add(String username, String password, String email);
    @Select("select * from user where email=#{email}")
    User findByEmail(String email);
    @Update("update user set password=#{password},update_time=now() where email=#{email}")
    void updatePassword(String email,String password);
    @Delete("delete from user where email=#{email}")
    void delete(String email);
}

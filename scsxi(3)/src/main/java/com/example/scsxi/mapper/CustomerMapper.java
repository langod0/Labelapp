package com.example.scsxi.mapper;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface CustomerMapper {

    @Insert("insert into upload(name,file,email) values(#{name},#{file},#{email})")
    void upload(String name, byte[] file,String email);
    @Select("SELECT name, done,file FROM upload WHERE email = #{email}")
    List<Map<String,Object>> getdata(String email);
    @Delete("DELETE FROM upload WHERE name = #{name} AND email = #{email}")
    void deleteFromUpload(String name, String email);
    @Delete("DELETE FROM dataset WHERE name = #{name} AND email = #{email}")
    void deleteFromDataset(String name, String email);
    @Select("SELECT * FROM dataset WHERE name = #{name} AND email = #{email}")
    List<Map<String, Object>> checkresult(String name, String email);

}

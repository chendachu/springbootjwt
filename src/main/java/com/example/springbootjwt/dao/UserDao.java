package com.example.springbootjwt.dao;

import com.example.springbootjwt.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {
    public User login(@Param("name") String name, @Param("pwd") String pwd);
}

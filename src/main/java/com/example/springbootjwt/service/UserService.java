package com.example.springbootjwt.service;

import com.example.springbootjwt.entity.User;

public interface UserService {
    public User login(String name,String pwd);
}

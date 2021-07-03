package com.example.springbootjwt.service.impl;

import com.example.springbootjwt.dao.UserDao;
import com.example.springbootjwt.entity.User;
import com.example.springbootjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User login(String name, String pwd) {
        User user = userDao.login(name, pwd);
        if (user != null){
            return user;
        }
        throw new RuntimeException("登录失败");
    }
}

package com.jupiterboy.springboot.mybatis.service.impl;

import com.jupiterboy.springboot.mybatis.entity.User;
import com.jupiterboy.springboot.mybatis.mapper.UserMapper;
import com.jupiterboy.springboot.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceimpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public User insertUser(User user) {
        userMapper.insertUser(user);
        return user;
    }
}
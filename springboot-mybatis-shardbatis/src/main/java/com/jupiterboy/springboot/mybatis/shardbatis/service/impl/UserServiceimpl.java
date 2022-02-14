package com.jupiterboy.springboot.mybatis.shardbatis.service.impl;

import com.jupiterboy.springboot.mybatis.shardbatis.entity.MyUser;
import com.jupiterboy.springboot.mybatis.shardbatis.mapper.UserMapper;
import com.jupiterboy.springboot.mybatis.shardbatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceimpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<MyUser> findAll() {
        return userMapper.findAll();
    }

    @Override
    public MyUser insertUser(MyUser user) {
        userMapper.insertUser(user);
        return user;
    }
}
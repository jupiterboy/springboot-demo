package com.jupiterboy.springboot.mybatis.service;

import com.jupiterboy.springboot.mybatis.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User insertUser(User user);
}

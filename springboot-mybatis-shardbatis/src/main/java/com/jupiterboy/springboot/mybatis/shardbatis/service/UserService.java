package com.jupiterboy.springboot.mybatis.shardbatis.service;

import com.jupiterboy.springboot.mybatis.shardbatis.entity.MyUser;

import java.util.List;

public interface UserService {
    List<MyUser> findAll();

    MyUser insertUser(MyUser user);
}

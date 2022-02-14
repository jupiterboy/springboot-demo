package com.jupiterboy.springboot.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jupiterboy.springboot.mybatisplus.entity.User;

import java.util.List;

public interface UserService extends IService<User> {
    List<User> findAll();
}

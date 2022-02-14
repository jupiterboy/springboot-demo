package com.jupiterboy.springboot.mybatis.mapper;

import com.jupiterboy.springboot.mybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> findAll();

    int insertUser(User user);
}

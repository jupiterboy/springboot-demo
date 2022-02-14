package com.jupiterboy.springboot.mybatis.shardbatis.mapper;

import com.jupiterboy.springboot.mybatis.shardbatis.entity.MyUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<MyUser> findAll();

    int insertUser(MyUser user);

    void createTable(String tableName);
}

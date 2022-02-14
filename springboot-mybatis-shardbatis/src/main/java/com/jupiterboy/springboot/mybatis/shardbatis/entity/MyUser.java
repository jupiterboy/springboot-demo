package com.jupiterboy.springboot.mybatis.shardbatis.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MyUser implements Serializable {

    private String id;//编号
    private String username;//用户名
    private String password;//密码
}

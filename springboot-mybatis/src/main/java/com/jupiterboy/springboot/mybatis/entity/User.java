package com.jupiterboy.springboot.mybatis.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class User implements Serializable {

    private Long id;//编号
    private String username;//用户名
    private String password;//密码
}

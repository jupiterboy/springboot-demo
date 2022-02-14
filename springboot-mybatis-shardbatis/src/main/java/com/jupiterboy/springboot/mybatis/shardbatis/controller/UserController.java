package com.jupiterboy.springboot.mybatis.shardbatis.controller;

import com.jupiterboy.springboot.mybatis.shardbatis.entity.MyUser;
import com.jupiterboy.springboot.mybatis.shardbatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAll")
    public List<MyUser> findAll(){
        return userService.findAll();
    }

    @RequestMapping("/insert/{userName}")
    @ResponseBody
    public MyUser insert(@PathVariable String userName){
        System.out.println("insert");

        MyUser user = new MyUser();
        user.setUsername(userName);
        user.setPassword("123456");
        return userService.insertUser(user);
    }

}
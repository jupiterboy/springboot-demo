package com.jupiterboy.springboot.mybatis.controller;

import com.jupiterboy.springboot.mybatis.entity.User;
import com.jupiterboy.springboot.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<User> findAll(){
        return userService.findAll();
    }

    @RequestMapping("/insert")
    @ResponseBody
    public User insert(){
        System.out.println("insert");
        User user = new User();
        user.setUsername("ggsd");
        user.setPassword("wter");
        return userService.insertUser(user);
    }

}
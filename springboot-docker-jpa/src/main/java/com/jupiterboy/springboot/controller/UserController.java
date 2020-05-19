package com.jupiterboy.springboot.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jupiterboy.springboot.entity.User;
import com.jupiterboy.springboot.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    UserService userService;
    
    @RequestMapping("/list")
    @ResponseBody
    public List<User> list() {
        return userService.getUserList();
    }
    
    @RequestMapping("/add/{name}")
    @ResponseBody
    public User add(@PathVariable String name) {
    	User user = new User();
    	user.setUserName(name);
    	user.setPassword("111111");
    	return userService.save(user);
    }

}

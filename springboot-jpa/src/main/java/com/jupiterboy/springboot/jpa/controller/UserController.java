package com.jupiterboy.springboot.jpa.controller;

import java.util.List;

import javax.annotation.Resource;

import com.jupiterboy.springboot.jpa.entity.User;
import com.jupiterboy.springboot.jpa.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    IUserService userService;

    @RequestMapping("/list")
    @ResponseBody
    public List<User> list() {
        return userService.getUsers();
    }
    
    @RequestMapping("/add/{name}")
    @ResponseBody
    public User add(@PathVariable String name) {
    	User user = new User();
    	user.setUserName(name);
    	user.setPassword("111111");
    	return userService.save(user);
    }

    @RequestMapping("/get/{id}")
    @ResponseBody
    public User get(@PathVariable String id) {
        return userService.get(id);
    }

}

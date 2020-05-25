package com.jupiterboy.springboot.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jupiterboy.springboot.entity.User;
import com.jupiterboy.springboot.service.IUserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    IUserService IUserService;

    @RequestMapping("/list")
    @ResponseBody
    public List<User> list() {
        return IUserService.getUsers();
    }
    
    @RequestMapping("/add/{name}")
    @ResponseBody
    public User add(@PathVariable String name) {
    	User user = new User();
    	user.setUserName(name);
    	user.setPassword("111111");
    	return IUserService.save(user);
    }

    @RequestMapping("/get/{id}")
    @ResponseBody
    public User get(@PathVariable String id) {
        return IUserService.get(id);
    }

}

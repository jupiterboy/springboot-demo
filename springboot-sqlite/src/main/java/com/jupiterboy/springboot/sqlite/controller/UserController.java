package com.jupiterboy.springboot.sqlite.controller;

import com.jupiterboy.springboot.sqlite.entity.User;
import com.jupiterboy.springboot.sqlite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

// http://localhost:8080/add?name=test
// http://localhost:8080/list
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/add")
    public User add(String name){
        User user = new User();
        user.setName(name);
        return userRepository.save(user);
    }

    @RequestMapping("/list")
    public Iterable<User> list(){
        Iterable<User> all = userRepository.findAll();
        return all;
    }
}
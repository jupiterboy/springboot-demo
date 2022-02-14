package com.jupiterboy.springboot.cache.controller;

import com.jupiterboy.springboot.cache.entity.User;
import com.jupiterboy.springboot.cache.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
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
        user.setAge(11);
    	return userService.save(user);
    }

    @RequestMapping("/get/{id}")
    @ResponseBody
    public User get(@PathVariable String id) {
        return userService.get(id);
    }

    @RequestMapping("/update/{id}/{name}")
    @ResponseBody
    public User update(@PathVariable String id, @PathVariable String name) {
        User user = userService.get(id);
        if(user != null) {
            user.setUserName(name);
            return userService.update(user);
        }else{
            log.error("not found entity, id:{}", id);
        }
        return null;
    }


    @RequestMapping("/delete/{id}")
    @ResponseBody
    public void delete(@PathVariable String id) {
        try {
            userService.delete(id);
        } catch (EmptyResultDataAccessException e) {
            log.error("not found entity, id:{}", id);
        }
    }


    @RequestMapping("/evict/{id}")
    @ResponseBody
    public void evict(@PathVariable String id) {
        userService.evict(id);
    }

}

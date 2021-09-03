package com.jupiterboy.springboot.sqlite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

// http://localhost:8080/add?name=test
// http://localhost:8080/list
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    /**
     * 新增一条数据
     * @param name
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public User add(String name){
        User user = new User();
        user.setName(name);
        return userRepository.save(user);
    }
    /**
     * 查询所有数据
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Iterable<User> list(){
        Iterable<User> all = userRepository.findAll();
        return all;
    }
}
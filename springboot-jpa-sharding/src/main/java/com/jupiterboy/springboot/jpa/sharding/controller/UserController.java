package com.jupiterboy.springboot.jpa.sharding.controller;

import com.jupiterboy.springboot.jpa.sharding.entity.User;
import com.jupiterboy.springboot.jpa.sharding.repository.UserRepository;
import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class UserController {


    @Autowired
    private SnowflakeShardingKeyGenerator snowflakeShardingKeyGenerator;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/user/save")
    @ResponseBody
    public String save() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date timestamp = sdf.parse("2022-04-01");
        for (int i = 0; i <10 ; i++) {
            User user=new User();
            user.setId((Long)snowflakeShardingKeyGenerator.generateKey());
            user.setName("test"+i);
            user.setCityId(i%2==0?1:2);
            user.setCreateTime(new Date());
            user.setSex(i%2);
            user.setPhone("11111111"+i);
            user.setEmail("xxxxx");
            user.setCreateTime(timestamp);
            user.setPassword("eeeeeeeeeeee");
            userRepository.save(user);
        }

        return "success";
    }

    @RequestMapping("/user/get/{id}")
    @ResponseBody
    public User get(@PathVariable Long id) {
        User user =  userRepository.getOne(id);
        System.out.println(user.getId());
        return user;
    }

}

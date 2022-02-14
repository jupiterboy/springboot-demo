package com.jupiterboy.springboot.mybatis.shardbatis;

import com.jupiterboy.springboot.mybatis.shardbatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class CreateTableTask {

    @Autowired
    private UserMapper userMapper;

    @Scheduled(cron="0 */1 * * * ?")
    public void createTable(){
        System.out.println("create table");
    }
}

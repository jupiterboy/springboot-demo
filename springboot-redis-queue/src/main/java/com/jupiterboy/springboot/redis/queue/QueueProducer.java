package com.jupiterboy.springboot.redis.queue;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class QueueProducer {

    @Autowired
    private StringRedisTemplate redisTemplate;
    //redis的消息队列直接使用redis数组实现
    private ListOperations<String, String> listRedis;

    /**
     * <br>描 述: 初始化时赋值
     * <br>作 者: shizhenwei
     * <br>历 史: (版本) 作者 时间 注释
     */
    @PostConstruct
    private void init(){
        listRedis = redisTemplate.opsForList();
    }

    public void produce(String queue, String message){
        System.out.println("produce message:" + message);
        listRedis.leftPush(queue, message);
    }

}

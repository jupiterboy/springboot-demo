package com.jupiterboy.springboot.redis.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class PublishService {

    @Autowired
    StringRedisTemplate redisTemplate;

    public void publish(String topic, Object message) {
        redisTemplate.convertAndSend(topic, message);
    }
}
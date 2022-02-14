package com.jupiterboy.springboot.redis.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class RedisTopicApplication {

    @Autowired
    private PublishService publishService;

    public static void main(String[] args) {
        SpringApplication.run(RedisTopicApplication.class, args);
    }


    @Scheduled(cron = "0/5 * * * * ?")
    public void execute(){
        publishService.publish("dj", "hello world");
    }
}

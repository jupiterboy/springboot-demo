package com.jupiterboy.springboot.redis.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class RedisQueueApplication {

    @Autowired
    private QueueProducer queueProducer;


    public static void main(String[] args) {
        SpringApplication.run(RedisQueueApplication.class, args);
    }

    @Scheduled(cron = "0/5 * * * * ?")
    public void execute(){
        queueProducer.produce("storage", "hello world");
    }

}

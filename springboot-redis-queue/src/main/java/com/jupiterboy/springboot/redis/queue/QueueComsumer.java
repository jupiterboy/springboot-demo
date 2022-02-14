package com.jupiterboy.springboot.redis.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class QueueComsumer {

    @Autowired
    private StringRedisTemplate redisTemplate;
    private ListOperations<String, String> listRedis;

    ExecutorService executorService = Executors.newSingleThreadExecutor();

    @PostConstruct
    private void init(){
        listRedis = redisTemplate.opsForList();

        executorService.submit(new Runnable() {

            @Override
            public void run() {
                while(true){
                    //从右边取堆栈顺序取1~10个消息
                    String msg = listRedis.rightPop("storage");
                    if(!StringUtils.isEmpty(msg)){
                        System.out.println("comsume message:" + msg);
                    }
                }
            }
        });
    }

}

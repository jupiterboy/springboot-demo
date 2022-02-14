package com.jupiterboy.springboot.eventtokafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SpringEventPublisher {

    @Autowired
    private ApplicationContext applicationContext;  //容器事件由容器触发

    public void publish(String msg) {
        log.info("publish msg:{}", msg);
        applicationContext.publishEvent(new SpringEvent(applicationContext, msg));
    }
}

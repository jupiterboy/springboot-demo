package com.jupiterboy.springboot.event;

import com.jupiterboy.springboot.event.MyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MyEventPublisher {

    @Autowired
    private ApplicationContext applicationContext;  //容器事件由容器触发

    public void publish(String msg) {
        System.out.println("发布事件：" + msg);
        applicationContext.publishEvent(new MyEvent(applicationContext, msg));
    }
}

package com.jupiterboy.springboot.event;

import com.jupiterboy.springboot.event.MyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MyEventListener implements ApplicationListener<MyEvent> {

    @Override
    public void onApplicationEvent(MyEvent event) {
        System.out.println("MyEventListener.onApplicationEvent");
        System.out.println("MyEventListener.onApplicationEvent: " + event.getSource());
        System.out.println("MyEventListener.onApplicationEvent: " + event.getMsg());
    }

}

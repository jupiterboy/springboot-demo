package com.jupiterboy.springboot.amq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @JmsListener(destination = "ActiveMQQueue")
    public void onMessage(String msg){
        System.out.println("received:" + msg);
    }
}

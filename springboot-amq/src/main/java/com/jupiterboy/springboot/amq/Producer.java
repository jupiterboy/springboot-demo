package com.jupiterboy.springboot.amq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.jms.Queue;

@Component
public class Producer {

    @Autowired
    private JmsMessagingTemplate messagingTemplate;

    @Autowired
    private Queue queue;

    @PostConstruct
    public void init(){
        messagingTemplate.convertAndSend(queue,"hello world1");
        messagingTemplate.convertAndSend(queue,"hello world2");
        messagingTemplate.convertAndSend(queue,"hello world3");
    }
}

package com.jupiterboy.springboot.rabbitmq.sender;

import com.jupiterboy.springboot.rabbitmq.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloSender {
    @Autowired
    private AmqpTemplate template;

    public void send() {
        template.convertAndSend("queue","hello,rabbit~");
    }


    public void send1() {
        User user=new User();    //实现Serializable接口
        user.setUsername("hlhdidi");
        user.setPassword("123");
        template.convertAndSend("queue",user);
    }

    public void send2() {
        template.convertAndSend("exchange","topic.message","hello,rabbit~~~11");
        template.convertAndSend("exchange","topic.messages","hello,rabbit~~~22");
    }

    public void send3() {
        template.convertAndSend("fanout","","xixi,haha");
    }

}
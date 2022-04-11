package com.jupiterboy.sprintboot.rabbitmq.sender;

import com.jupiterboy.springboot.rabbitmq.sender.HelloSender;
import com.jupiterboy.springboot.rabbitmq.sender.SenderApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes= SenderApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestRabbitMQ {

    @Autowired
    private HelloSender helloSender;

    @Test
    public void testRabbit() {
//        helloSender.send();
//        helloSender.send1();
//        helloSender.send2();
        helloSender.send3();

    }
}
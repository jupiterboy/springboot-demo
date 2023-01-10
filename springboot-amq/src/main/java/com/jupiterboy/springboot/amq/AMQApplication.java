package com.jupiterboy.springboot.amq;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import javax.jms.Queue;

@EnableJms
@SpringBootApplication
public class AMQApplication {

    public static void main(String[] args) {
        SpringApplication.run(AMQApplication.class, args);
    }

    @Bean
    public Queue queue(){
        return new ActiveMQQueue("ActiveMQQueue");
    }
}

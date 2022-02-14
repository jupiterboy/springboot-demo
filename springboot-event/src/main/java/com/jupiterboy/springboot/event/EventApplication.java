package com.jupiterboy.springboot.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EventApplication {

    @Autowired
    MyEventPublisher myEventPublisher;

    public static void main(String[] args) {
        SpringApplication.run(EventApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {
            myEventPublisher.publish("hello world");
        };
    }
}

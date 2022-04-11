package com.jupiterboy.springboot.rabbitmq.sender;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// ref: https://www.cnblogs.com/linyufeng/p/9885645.html
@SpringBootApplication
public class SenderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SenderApplication.class, args);
    }
}

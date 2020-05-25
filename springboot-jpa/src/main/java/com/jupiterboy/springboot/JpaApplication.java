package com.jupiterboy.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootApplication
public class JpaApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(JpaApplication.class, args);
    }
}


package com.jupiterboy.springboot.timeit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class TimeItApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimeItApplication.class,args);
    }


    @Bean
    @TimeIt
    public void test(){
        try {
            TimeUnit.SECONDS.sleep(2L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

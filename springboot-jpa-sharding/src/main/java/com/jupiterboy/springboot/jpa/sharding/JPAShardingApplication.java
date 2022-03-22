package com.jupiterboy.springboot.jpa.sharding;

import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Calendar;

@EnableScheduling
@SpringBootApplication
public class JPAShardingApplication {

    @Bean
    public SnowflakeShardingKeyGenerator snowflakeShardingKeyGenerator(){
        return new SnowflakeShardingKeyGenerator();
    }
    public static void main(String[] args) {
        System.out.println(Calendar.getInstance().get(Calendar.MONTH));
        SpringApplication.run(JPAShardingApplication.class, args);
    }
}

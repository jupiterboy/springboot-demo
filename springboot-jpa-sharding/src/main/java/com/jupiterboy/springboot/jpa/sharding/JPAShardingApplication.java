package com.jupiterboy.springboot.jpa.sharding;

import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JPAShardingApplication {

    @Bean
    public SnowflakeShardingKeyGenerator snowflakeShardingKeyGenerator(){
        return new SnowflakeShardingKeyGenerator();
    }
    public static void main(String[] args) {
        SpringApplication.run(JPAShardingApplication.class, args);
    }
}

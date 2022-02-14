package com.jupiterboy.springboot.mybatis.shardbatis;


import com.google.code.shardbatis.plugin.ShardPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class MyBatisConfiguration {

//    @Bean
//    public MyMybatisInterceptor myMybatisInterceptor(){
//        return new MyMybatisInterceptor();
//    }

    @Bean
    public ShardPlugin shardPlugin(){
        ShardPlugin shardPlugin = new MyShardPlugin();
        Properties properties = new Properties();
        properties.setProperty("shardingConfig", "shard_config.xml");
        shardPlugin.setProperties(properties);
        return shardPlugin;
    }
}

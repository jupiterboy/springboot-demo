package com.jupiterboy.springboot.mybatis.shardbatis;

import com.jupiterboy.springboot.mybatis.shardbatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.jupiterboy.springboot.mybatis.shardbatis.mapper")//使用MapperScan批量扫描所有的Mapper接口
public class MybatisApplication implements CommandLineRunner {

	@Autowired
	private UserMapper userMapper;

	public static void main(String[] args) {
		SpringApplication.run(MybatisApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userMapper.createTable("tb_user_"+System.currentTimeMillis()%100);
	}
}

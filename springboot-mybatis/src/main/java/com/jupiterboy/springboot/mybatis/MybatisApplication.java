package com.jupiterboy.springboot.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.jupiterboy.springboot.mybatis.mapper")//使用MapperScan批量扫描所有的Mapper接口
public class MybatisApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MybatisApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}

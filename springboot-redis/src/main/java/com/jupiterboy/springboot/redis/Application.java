package com.jupiterboy.springboot.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.StringRedisTemplate;

@EnableCaching
@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private StringRedisTemplate template;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Customer c = new Customer();
		c.setId(""+System.currentTimeMillis());
		c.setAge(11);
		c.setAddress("dsafdsfdsa");
		c.setName("dsaf");
		template.opsForValue().set("111", c.toString());
	}
}

package com.jupiterboy.springboot.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

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
//		Customer c = new Customer();
//		c.setId(""+System.currentTimeMillis());
//		c.setAge(11);
//		c.setAddress("dsafdsfdsa");
//		c.setName("dsaf");
//		template.opsForValue().set("aa1", c.toString(), 1000, TimeUnit.SECONDS);
//		template.opsForValue().set("aa2", c.toString(), 1100, TimeUnit.SECONDS);
//		template.opsForValue().set("aa3", c.toString(), 1200, TimeUnit.SECONDS);

		template.keys("aa*").stream().map(key -> template.opsForValue().get(key)).forEach(System.out::println);

	}
}

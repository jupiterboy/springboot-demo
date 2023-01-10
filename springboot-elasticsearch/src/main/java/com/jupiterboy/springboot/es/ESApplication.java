package com.jupiterboy.springboot.es;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ESApplication implements CommandLineRunner {

	@Autowired TestService testService;

	public static void main(String[] args) {
		SpringApplication.run(ESApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		testService.search();
	}
}

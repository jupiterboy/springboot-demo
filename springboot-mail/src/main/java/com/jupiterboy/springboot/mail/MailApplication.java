package com.jupiterboy.springboot.mail;

import com.jupiterboy.springboot.mail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MailApplication  implements CommandLineRunner {

	@Autowired
	private MailService mailService;
	
	public static void main(String[] args) {
		SpringApplication.run(MailApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		mailService.sendSimpleMail("yuhai@tcb.com.cn", "Test", "Test");
		
	}
}

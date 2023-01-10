package com.jupiterboy.springboot.jpa;

import com.jupiterboy.springboot.jpa.entity.Topic;
import com.jupiterboy.springboot.jpa.entity.User;
import com.jupiterboy.springboot.jpa.repository.TopicRepository;
import com.jupiterboy.springboot.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class JpaApplication extends SpringBootServletInitializer {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TopicRepository topicRepository;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(JpaApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(JpaApplication.class, args);
    }

    @Bean
    public void commandLineRunner(){
//        User user = new User();
//        user.setUserName("2132");
//        user.setPassword("dasf");
//        user.setAge(11);
//        user = userRepository.save(user);
//
//        Topic topic = new Topic();
//        topic.setName("adfsfdas");
//        topic.setUser(user);
//        topicRepository.save(topic);

        topicRepository.deleteById("4028b2f080db5b320180db5b368e0001");
//        userRepository.deleteById("4028b2f080db5b320180db5b36550000");
    }
}


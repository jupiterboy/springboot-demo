package com.jupiterboy.springboot.jpa.snowflake;

import cn.hutool.core.lang.Snowflake;
import com.jupiterboy.springboot.jpa.snowflake.entity.Department;
import com.jupiterboy.springboot.jpa.snowflake.entity.User;
import com.jupiterboy.springboot.jpa.snowflake.repository.DepartmentRepository;
import com.jupiterboy.springboot.jpa.snowflake.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class SnowflakeApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public static void main(String[] args) {
        SpringApplication.run(SnowflakeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        Department department = new Department();
//        department.setDepartmentName("开发部");
//        User user = new User();
//        user.setUserName("jupiter");
//        user.setPassword("111");
//        user.setDepartment(department);
//        department.getUsers().add(user);
//        departmentRepository.save(department);

        User user = userRepository.findById(1503885726994010112L).orElse(null);
        System.out.println(user.getDepartment());

    }

    @Bean
    public Snowflake snowflake() {
        return new Snowflake(1, 1);
    }
}

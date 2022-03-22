package com.jupiterboy.springboot.jpa;

import com.jupiterboy.springboot.jpa.entity.Department;
import com.jupiterboy.springboot.jpa.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class JpaApplication {


    @Autowired
    DepartmentRepository departmentRepository;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(JpaApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {
//            createRoot();
            queryRoot();
        };

    }

    private void createRoot(){
        Department root = new Department();
        root.setDepartmentName("root");

        List<Department> children = new ArrayList<>();

        for(int i=0;i<5;i++){
            Department child = new Department();;
            child.setDepartmentName("child-"+i);

            children.add(child);
        }
        root.setChildren(children);

        departmentRepository.save(root);
    }

    private void queryRoot(){
        Department root = departmentRepository.findById("a3c0bedd-75b1-42b4-9e0a-dfefe18ac876").orElse(null);
        System.out.println(root);
    }
}


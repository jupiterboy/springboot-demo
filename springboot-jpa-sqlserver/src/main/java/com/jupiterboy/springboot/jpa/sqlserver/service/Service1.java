package com.jupiterboy.springboot.jpa.sqlserver.service;

import com.jupiterboy.springboot.jpa.sqlserver.entity.Student;
import com.jupiterboy.springboot.jpa.sqlserver.entity.Teacher;
import com.jupiterboy.springboot.jpa.sqlserver.repository.StudentRepository;
import com.jupiterboy.springboot.jpa.sqlserver.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class Service1 {
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    private static final int SIZE = 20;


    private static ExecutorService insertService = Executors.newFixedThreadPool(SIZE);

    private static Random random = new Random();

//    @PostConstruct
    public void init(){
        Executors.newSingleThreadExecutor().submit(() -> {
            for(int i=0;i<SIZE;i++){
                insertService.submit(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            service();
                        }
                    }
                });
            }
        });
    }


    @Transactional
    public void service(){
        try{

            int id1 = random.nextInt(10000)+1;
            Student s = studentRepository.getOne(id1);

            TimeUnit.MILLISECONDS.sleep(random.nextInt(300));

            int id2  = random.nextInt(10000)+1;
            Teacher t = teacherRepository.getOne(id2);


        }catch (Exception e){
            e.printStackTrace();
        }

    }


}

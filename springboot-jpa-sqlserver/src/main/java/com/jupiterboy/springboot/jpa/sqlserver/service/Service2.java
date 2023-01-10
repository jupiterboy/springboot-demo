package com.jupiterboy.springboot.jpa.sqlserver.service;

import com.jupiterboy.springboot.jpa.sqlserver.entity.Student;
import com.jupiterboy.springboot.jpa.sqlserver.entity.Teacher;
import com.jupiterboy.springboot.jpa.sqlserver.repository.StudentRepository;
import com.jupiterboy.springboot.jpa.sqlserver.repository.TeacherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class Service2 {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    private static final int SIZE = 20;
    private static ExecutorService queryService = Executors.newFixedThreadPool(SIZE);

    private static Random random = new Random();

//    @PostConstruct
    public void init(){
        Executors.newSingleThreadExecutor().submit(() -> {
            for(int i=0; i<SIZE; i++){
                queryService.submit(new Runnable() {
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

            Student s = new Student();
//            int id1 = random.nextInt(200000)+1;
//            Student s = studentRepository.getOne(id1);
            s.setUserName("student");
            s.setPassword("password");
            s.setAge(10);
            studentRepository.save(s);

            TimeUnit.MILLISECONDS.sleep(random.nextInt(5));

//            int id2  = random.nextInt(200000)+1;
//            Teacher t = teacherRepository.getOne(id2);
            Teacher t = new Teacher();
            t.setUserName("teacher");
            t.setPassword("password");
            t.setAge(10);
            teacherRepository.save(t);


        }catch (EntityNotFoundException e1){
            log.info(e1.getMessage());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

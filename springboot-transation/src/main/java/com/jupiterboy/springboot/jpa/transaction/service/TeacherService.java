package com.jupiterboy.springboot.jpa.transaction.service;

import com.jupiterboy.springboot.jpa.transaction.entity.Teacher;
import com.jupiterboy.springboot.jpa.transaction.repository.TeacherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Transactional(propagation= Propagation.NEVER)
    public void add(){
        Teacher t = new Teacher();
        t.setUserName("teacher");
        t.setPassword("password");
        t.setAge(10);
        teacherRepository.save(t);
    }
}

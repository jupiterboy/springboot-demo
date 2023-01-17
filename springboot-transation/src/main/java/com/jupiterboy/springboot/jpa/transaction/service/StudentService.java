package com.jupiterboy.springboot.jpa.transaction.service;

import com.jupiterboy.springboot.jpa.transaction.entity.Student;
import com.jupiterboy.springboot.jpa.transaction.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Transactional(propagation= Propagation.NEVER)
    public void add() {
        Student s = new Student();
        s.setUserName("student");
        s.setPassword("password");
        s.setAge(10);
        studentRepository.save(s);
    }
}

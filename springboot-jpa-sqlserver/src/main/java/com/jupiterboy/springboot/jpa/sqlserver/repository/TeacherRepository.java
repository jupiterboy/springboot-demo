package com.jupiterboy.springboot.jpa.sqlserver.repository;

import com.jupiterboy.springboot.jpa.sqlserver.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

}
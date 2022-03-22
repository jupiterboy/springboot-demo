package com.jupiterboy.springboot.jpa.repository;

import com.jupiterboy.springboot.jpa.entity.Department;
import com.jupiterboy.springboot.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, String> {

}
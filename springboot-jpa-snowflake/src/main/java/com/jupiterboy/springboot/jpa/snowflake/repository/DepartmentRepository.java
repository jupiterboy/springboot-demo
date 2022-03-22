package com.jupiterboy.springboot.jpa.snowflake.repository;

import com.jupiterboy.springboot.jpa.snowflake.entity.Department;
import com.jupiterboy.springboot.jpa.snowflake.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
package com.jupiterboy.springboot.jpa.snowflake.repository;

import com.jupiterboy.springboot.jpa.snowflake.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
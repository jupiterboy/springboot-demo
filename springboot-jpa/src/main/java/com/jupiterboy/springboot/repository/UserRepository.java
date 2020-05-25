package com.jupiterboy.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jupiterboy.springboot.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

}
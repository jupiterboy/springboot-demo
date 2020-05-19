package com.jupiterboy.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jupiterboy.springboot.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findById(String id);

    void deleteById(String id);
}
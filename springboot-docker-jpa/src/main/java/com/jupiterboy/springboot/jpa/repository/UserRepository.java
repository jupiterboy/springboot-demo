package com.jupiterboy.springboot.jpa.repository;

import java.util.Optional;

import com.jupiterboy.springboot.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findById(String id);

    void deleteById(String id);
}
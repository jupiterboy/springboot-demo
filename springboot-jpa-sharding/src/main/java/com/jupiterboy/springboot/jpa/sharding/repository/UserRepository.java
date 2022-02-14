package com.jupiterboy.springboot.jpa.sharding.repository;

import com.jupiterboy.springboot.jpa.sharding.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
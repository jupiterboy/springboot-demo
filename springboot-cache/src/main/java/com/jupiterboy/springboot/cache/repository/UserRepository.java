package com.jupiterboy.springboot.cache.repository;

import com.jupiterboy.springboot.cache.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
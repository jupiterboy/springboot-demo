package com.jupiterboy.springboot.jpa.repository;

import com.jupiterboy.springboot.jpa.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, String> {

}
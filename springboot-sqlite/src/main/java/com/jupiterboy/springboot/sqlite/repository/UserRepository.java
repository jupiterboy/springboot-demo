package com.jupiterboy.springboot.sqlite.repository;

import com.jupiterboy.springboot.sqlite.entity.User;
import org.springframework.data.repository.CrudRepository;

public  interface UserRepository extends CrudRepository<User, String> {

}

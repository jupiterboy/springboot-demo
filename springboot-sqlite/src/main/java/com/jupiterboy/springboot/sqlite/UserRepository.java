package com.jupiterboy.springboot.sqlite;

import org.springframework.data.repository.CrudRepository;

public  interface UserRepository extends CrudRepository<User, String> {

}

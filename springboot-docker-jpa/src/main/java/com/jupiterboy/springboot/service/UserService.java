package com.jupiterboy.springboot.service;

import java.util.List;

import com.jupiterboy.springboot.entity.User;

public interface UserService {

    public List<User> getUserList();

    public User findUserById(String id);

    public User save(User user);

    public void edit(User user);

    public void delete(String id);


}

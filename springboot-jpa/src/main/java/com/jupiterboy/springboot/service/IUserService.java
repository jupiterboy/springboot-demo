package com.jupiterboy.springboot.service;

import java.util.List;

import com.jupiterboy.springboot.entity.User;

public interface IUserService {

    public List<User> getUsers();

    public User get(String id);

    public User save(User user);

    public void update(User user);

    public void delete(String id);


}

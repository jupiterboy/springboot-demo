package com.jupiterboy.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jupiterboy.springboot.entity.User;
import com.jupiterboy.springboot.repository.UserRepository;

@Service
public class IUserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User get(String id) {
        return userRepository.getOne(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(String id) {
        userRepository.deleteById(id);
    }
}



package com.jupiterboy.springboot.cache.service.impl;

import com.jupiterboy.springboot.cache.entity.User;
import com.jupiterboy.springboot.cache.repository.UserRepository;
import com.jupiterboy.springboot.cache.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Cacheable(value = "users")
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    @Cacheable(value = "user" ,key = "#id", condition="#result == null")
    public User get(String id) {
        User user = userRepository.findById(id).orElse(null);
        log.info("get user from db:{}", user);
        return user;
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Caching(evict = {
            @CacheEvict(value = "users", allEntries = true)
    },
    put = {
            @CachePut(value = "user", key = "#user.id")
    })
    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Caching(evict = {
            @CacheEvict(value = "users", allEntries = true),
            @CacheEvict(value = "user" ,key = "#id")
    })
    @Override
    public void delete(String id) {
        userRepository.deleteById(id);
    }

    @CacheEvict(value = "user" ,key = "#id")
    @Override
    public void evict(String id) {

    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void evictAll() {

    }
}



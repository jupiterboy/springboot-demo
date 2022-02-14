package com.jupiterboy.springboot.cache.service;

import com.jupiterboy.springboot.cache.entity.User;
import org.springframework.cache.annotation.CacheEvict;

import java.util.List;

public interface IUserService {

    public List<User> getUsers();

    public User get(String id);

    public User save(User user);

    public User update(User user);

    public void delete(String id);

    void evict(String id);

    @CacheEvict(value = "users", allEntries = true)
    void evictAll();
}

package com.jupiterboy.springboot.mybatisplus;

import com.jupiterboy.springboot.mybatisplus.entity.User;
import com.jupiterboy.springboot.mybatisplus.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleTest {
    @Autowired
    private UserMapper mapper;
    @Test
    public void testSelect() {
        List<User> list = mapper.selectList(null);
        assertEquals(5, list.size());
        list.forEach(System.out::println);
    }
}
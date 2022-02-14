package com.jupiterboy.springboot.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jupiterboy.springboot.mybatisplus.entity.User;
import com.jupiterboy.springboot.mybatisplus.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {
    @Autowired
    private UserService userService;
    @Test
    public void testGetOne() {
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
        wrapper.gt(User::getAge, 28);
        User one = userService.getOne(wrapper, false); // 第二参数指定为false,使得在查到了多行记录时,不抛出异常,而返回第一条记录
        System.out.println(one);
    }

    @Test
    public void testChain() {
        List<User> list = userService.lambdaQuery()
                .gt(User::getAge, 20)
                .likeRight(User::getName, "黄")
                .list();
        list.forEach(System.out::println);
    }

    @Test
    public void testChain1() {
        userService.lambdaUpdate()
                .gt(User::getAge, 20)
                .likeRight(User::getName, "黄")
                .set(User::getEmail, "w39@baomidou.com")
                .update();
    }

    @Test
    public void testChain2() {
        userService.lambdaUpdate()
                .like(User::getName, "黄")
                .remove();
    }

}
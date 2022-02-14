package com.jupiterboy.springboot.dubbe.provider;

import java.util.Date;

import com.alibaba.dubbo.config.annotation.Service;
import com.jupiterboy.springboot.dubbe.api.DemoService;


@Service
public class DemoServiceImpl implements DemoService {
    public String sayHello(String name) {
        return new Date() + "-" + name;
    }
}
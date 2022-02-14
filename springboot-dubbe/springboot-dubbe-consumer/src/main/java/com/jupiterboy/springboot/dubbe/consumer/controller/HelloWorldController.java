package com.jupiterboy.springboot.dubbe.consumer.controller;

import com.jupiterboy.springboot.dubbe.api.DemoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;

@RestController
public class HelloWorldController {
	
	@Reference
	private DemoService demoService;

    @RequestMapping("/hello")
    public String helloworld() {
        return demoService.sayHello("Hello World!");
    }
}

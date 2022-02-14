package com.jupiterboy.springboot.elk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/index")
    public Object index() {
        logger.debug("======ELK2测试=======");
        logger.info("======ELK2测试=======");
        logger.warn("======ELK2测试=======");
        logger.error("======ELK2测试=======");

        return "success";
    }
}
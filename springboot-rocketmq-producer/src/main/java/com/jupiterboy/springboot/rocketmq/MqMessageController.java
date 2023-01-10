package com.jupiterboy.springboot.rocketmq;


import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rocketmq")
public class MqMessageController {
    private static final Logger log = LoggerFactory.getLogger(MqMessageController.class);

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @RequestMapping("/publish/{id}")
    public void publish(@PathVariable("id") int id) {
        rocketMQTemplate.convertAndSend("first-topic", "你好,Java旅途" + id);
        rocketMQTemplate.convertAndSend("first-topic", "你好,Java旅途" + id);
        rocketMQTemplate.convertAndSend("first-topic", "你好,Java旅途" + id);
    }
}
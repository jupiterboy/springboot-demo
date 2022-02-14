package com.jupiterboy.springboot.netty;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class WebController {

    @RequestMapping("/list/{deviceId}")
    @ResponseBody
    public List<Object> list(@PathVariable String deviceId) {
        return (List<Object>) NettyApplication.queues.get(deviceId).stream().collect(Collectors.toList());
    }
}

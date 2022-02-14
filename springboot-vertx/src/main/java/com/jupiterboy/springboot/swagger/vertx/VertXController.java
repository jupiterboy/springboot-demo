package com.jupiterboy.springboot.swagger.vertx;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VertXController {

    @RequestMapping("/vertx/{port}")
    public String vertx(@PathVariable int port) {
        Gateway gateway = new Gateway();
        gateway.start(port);
        return ""+port;
    }
}

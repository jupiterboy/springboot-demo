package com.jupiterboy.springboot.eventtokafka;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

@Getter
@Setter
public class SpringEvent extends ApplicationContextEvent {

    private String msg;

    public SpringEvent(ApplicationContext source, String msg) {
        super(source);
        this.msg = msg;
    }
}

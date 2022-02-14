package com.jupiterboy.springboot.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

@Getter
@Setter
public class MyEvent extends ApplicationContextEvent {

    private String msg;

    public MyEvent(ApplicationContext source, String msg) {
        super(source);
        this.msg = msg;
    }
}

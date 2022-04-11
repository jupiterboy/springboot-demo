package com.tcb.common.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public abstract class SpringApplicationEventListener<E extends ApplicationEvent> implements ApplicationListener<E>, EventListener<E> {

    private final ExecutorService EXECUTOR_SERVICE = Executors.newSingleThreadScheduledExecutor();

    @Override
    public void onApplicationEvent(E event) {
        log.debug("接收 {} 事件 @ {}", event.getClass().getSimpleName(), getClass().getName().split("\\.")[3]);
        EXECUTOR_SERVICE.submit(() -> onEvent(event));
    }
}

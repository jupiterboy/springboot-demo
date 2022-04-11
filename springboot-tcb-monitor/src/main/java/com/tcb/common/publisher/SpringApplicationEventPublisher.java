package com.tcb.common.publisher;

import com.tcb.common.event.SpringApplicationEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Arrays;

@Slf4j
@Component("com.tcb.common.publisher.SpringApplicationEventPublisher")
public class SpringApplicationEventPublisher implements EventPublisher{

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void publishEvent(Object event) {
        Assert.isTrue(event instanceof SpringApplicationEvent, "event must be an instance of SpringApplicationEvent");
        StackTraceElement element = Arrays.stream(Thread.currentThread().getStackTrace()).filter(s -> s.getClassName().startsWith("com.tcb.modules")).findFirst().orElse(null);
        String source = element == null ? "unknown" : element.getClassName().split("\\.")[3];
        log.debug("发布 {} 事件 @ {}, 来源 [{}]", event.getClass().getSimpleName(), source, ((SpringApplicationEvent)event).getSourceName());
        applicationContext.publishEvent(event);
    }
}

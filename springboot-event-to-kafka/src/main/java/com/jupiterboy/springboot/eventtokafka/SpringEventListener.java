package com.jupiterboy.springboot.eventtokafka;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class SpringEventListener implements ApplicationListener<SpringEvent> {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Override
    public void onApplicationEvent(SpringEvent event) {
        System.out.println("MyEventListener.onApplicationEvent");
        System.out.println("MyEventListener.onApplicationEvent: " + event.getSource());
        System.out.println("MyEventListener.onApplicationEvent: " + event.getMsg());

        KafkaMessage msg = new KafkaMessage();
        BeanUtils.copyProperties(event, msg);
        kafkaTemplate.send("test", msg);
    }

}

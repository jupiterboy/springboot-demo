package com.jupiterboy.springboot.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ZKWatcher implements Watcher {

    @Autowired
    private ZKClient zkClient;

    private static final Logger logger = LoggerFactory.getLogger(ZKWatcher.class);

    @Override
    public void process(WatchedEvent event) {
        logger.info("【Watcher监听事件】={}",event.getState());
        logger.info("【监听路径为】={}",event.getPath());
        logger.info("【监听的类型为】={}",event.getType()); //  三种监听类型： 创建，删除，更新
        logger.info("【监听值为】={}", zkClient.getData(event.getPath(), this));
    }
}
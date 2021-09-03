package com.jupiterboy.springboot.memcache;

import net.spy.memcached.MemcachedClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.InetSocketAddress;

@SpringBootApplication
public class Application implements CommandLineRunner {

    protected Logger logger =  LoggerFactory.getLogger(this.getClass());

    @Resource
    private  MemcacheSource memcacheSource;

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            MemcachedClient client = new MemcachedClient(new InetSocketAddress(memcacheSource.getIp(),memcacheSource.getPort()));
            client.set("testkey",1000,"666666");
            System.out.println("***********  "+ client.get("testkey").toString());
        } catch (IOException e) {
            logger.error("inint MemcachedClient failed ",e);
        }
    }

}

package com.jupiterboy.springboot.jpa.sqlserver;

import com.jupiterboy.springboot.jpa.sqlserver.service.Service1;
import com.jupiterboy.springboot.jpa.sqlserver.service.Service2;
import com.jupiterboy.springboot.jpa.sqlserver.service.Service3;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServiceTest {

    @Autowired
    private Service1 service1;
    @Autowired private Service2 service2;
    @Autowired private Service3 service3;

    private static final int SIZE = 10;

    private static ExecutorService executorService1 = Executors.newFixedThreadPool(SIZE);
    private static ExecutorService executorService2 = Executors.newFixedThreadPool(SIZE);
    private static ExecutorService executorService3 = Executors.newFixedThreadPool(SIZE);

    @Test
    public void test() throws InterruptedException {
        Executors.newSingleThreadExecutor().submit(() -> {
            for(int i=0;i<SIZE;i++){
                executorService1.submit(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            service1.service();
                        }
                    }
                });
            }
        });
        Executors.newSingleThreadExecutor().submit(() -> {
            for(int i=0;i<SIZE;i++){
                executorService2.submit(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            service2.service();
                        }
                    }
                });
            }
        });
        Executors.newSingleThreadExecutor().submit(() -> {
            for(int i=0;i<SIZE;i++){
                executorService3.submit(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            service3.service();
                        }
                    }
                });
            }
        });
        TimeUnit.HOURS.sleep(10);
    }
}

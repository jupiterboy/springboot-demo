package com.jupiterboy.springboot.retry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableRetry
public class RetryApplication implements CommandLineRunner {

    @Autowired
    private MyServcie myServcie;

    public static void main(String[] args) {
        SpringApplication.run(RetryApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        myServcie.service();
    }
}


@Service
@Slf4j
class MyServcie{

    @Retryable(value= {RemoteAccessException.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l,multiplier = 1))
    public void service()  {
        try {
            log.info("do something...");
            TimeUnit.SECONDS.sleep(3L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new RemoteAccessException("RemoteAccessException");
    }

    @Recover
    public void recover(RemoteAccessException e){
        System.out.println("recover");
    }
}

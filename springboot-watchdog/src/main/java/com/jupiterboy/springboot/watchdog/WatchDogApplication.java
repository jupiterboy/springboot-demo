package com.jupiterboy.springboot.watchdog;

import DLL.UT_DLL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@Slf4j
@SpringBootApplication
public class WatchDogApplication {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        if(check()) {
            SpringApplication.run(WatchDogApplication.class, args);
        }else{
            log.error("WatchDogApplication is not running");
        }
    }

    public static boolean check() {
        try{
            return new WatchDog().validate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}

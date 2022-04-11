package com.jupiterboy.springboot.watchdog;

import lombok.extern.slf4j.Slf4j;
import java.io.IOException;

@Slf4j
public class WatchDogApplication {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        if(check()) {
            log.info("success");
        }else{
            log.error("failed");
        }
    }

    public static boolean check() {
        try{
            return new WatchDog().test();
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}

package springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// ref: https://blog.csdn.net/MostSnails/article/details/121320013
@SpringBootApplication
@MapperScan("springboot.mapper")
public class ShardingApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ShardingApplication.class, args);
    }
}


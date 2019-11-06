package com.fork.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.fork.cache.mapper")
@SpringBootApplication
public class SpringbootCachedemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootCachedemoApplication.class, args);
    }

}

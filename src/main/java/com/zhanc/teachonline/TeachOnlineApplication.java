package com.zhanc.teachonline;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zhanc.teachonline.dao")
public class TeachOnlineApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeachOnlineApplication.class, args);
    }

}

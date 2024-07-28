package com.lf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lf.dao")
public class LfadminApplication {
    public static void main(String[] args) {
        SpringApplication.run(LfadminApplication.class, args);
    }
}

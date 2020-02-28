package com.yjx.individual;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yjx.individual.mapper")
public class IndividualApplication {

    public static void main(String[] args) {
        SpringApplication.run(IndividualApplication.class, args);
    }

}

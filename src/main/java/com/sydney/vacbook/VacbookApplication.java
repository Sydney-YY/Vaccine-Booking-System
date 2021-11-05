package com.sydney.vacbook;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sydney.vacbook.mapper")
public class VacbookApplication {

    public static void main(String[] args) {
        SpringApplication.run(VacbookApplication.class, args);
    }

}

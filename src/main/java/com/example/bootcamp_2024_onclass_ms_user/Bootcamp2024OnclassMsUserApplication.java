package com.example.bootcamp_2024_onclass_ms_user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.bootcamp_2024_onclass_ms_user"})
public class Bootcamp2024OnclassMsUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(Bootcamp2024OnclassMsUserApplication.class, args);
    }

}

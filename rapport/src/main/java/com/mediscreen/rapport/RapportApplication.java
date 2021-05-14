package com.mediscreen.rapport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.mediscreen.rapport")
public class RapportApplication {

    public static void main(String[] args) {
        SpringApplication.run(RapportApplication.class, args);
    }

}

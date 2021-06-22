package com.mediscreen.notes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@SpringBootApplication
public class NotesApplication extends CorsRegistry {

    public static void main(String[] args) {
        SpringApplication.run(NotesApplication.class, args);
    }


}

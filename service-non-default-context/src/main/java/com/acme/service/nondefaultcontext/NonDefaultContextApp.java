package com.acme.service.nondefaultcontext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NonDefaultContextApp {
    public static void main(String[] args) {
        SpringApplication.run(NonDefaultContextApp.class, args);
    }
}

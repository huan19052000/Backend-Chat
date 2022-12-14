package com.example.bruce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@PropertySource("classpath:application.properties")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}

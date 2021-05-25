package com.broker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    @Bean
//    CommandLineRunner spongebob() {
//        log.warn("Application.init");
//        System.out.println("Application.init");
//        return args -> {
//            log.warn("Application.init2");
//            System.out.println("Application.init2");
//        };
//    }

}

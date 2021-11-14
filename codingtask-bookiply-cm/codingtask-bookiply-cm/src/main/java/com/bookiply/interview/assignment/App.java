package com.bookiply.interview.assignment;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
                "com.bookiply.interview.service",
                "com.bookiply.interview.assignment",
                "com.bookiply.interview.dto"
})
public class App {

    public static void main(String[] args) {

        SpringApplication.run(App.class, args);

    }
}

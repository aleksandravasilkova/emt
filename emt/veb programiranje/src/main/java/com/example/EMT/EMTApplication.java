package com.example.EMT;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class EMTApplication {

    public static void main(String[] args) {
        SpringApplication.run(EMTApplication.class, args);
    }

}

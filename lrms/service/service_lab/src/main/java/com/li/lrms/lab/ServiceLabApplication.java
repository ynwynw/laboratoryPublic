package com.li.lrms.lab;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.li")
@SpringBootApplication

public class ServiceLabApplication {
    public static void main(String[] args) {

        SpringApplication.run(ServiceLabApplication.class,args);
    }
}

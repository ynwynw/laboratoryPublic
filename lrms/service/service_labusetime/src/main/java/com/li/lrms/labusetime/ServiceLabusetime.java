package com.li.lrms.labusetime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan(basePackages = {"com.li"})
@SpringBootApplication
@EnableScheduling
public class ServiceLabusetime {
    public static void main(String[] args) {
        SpringApplication.run(ServiceLabusetime.class,args);
    }
}

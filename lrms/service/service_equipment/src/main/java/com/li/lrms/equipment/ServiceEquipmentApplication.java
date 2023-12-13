package com.li.lrms.equipment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.li")
@SpringBootApplication
public class ServiceEquipmentApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceEquipmentApplication.class,args);
    }
}

package com.peanut;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.io.Serializable;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "com.peanut.dao")
public class ServicePartsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicePartsApplication.class,args);
    }

}

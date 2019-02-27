package com.peanut;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
//@EnableRedisHttpSession
@MapperScan(basePackages = "com.peanut.dao")
public class ServicePhoneApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicePhoneApplication.class,args);
    }

}

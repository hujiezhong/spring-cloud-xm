package com.peanut;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
//@EnableRedisHttpSession
@MapperScan(basePackages = "com.peanut.dao")
public class WebPhoneFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebPhoneFeignApplication.class,args);
    }

}

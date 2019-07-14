package com.peanut;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

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

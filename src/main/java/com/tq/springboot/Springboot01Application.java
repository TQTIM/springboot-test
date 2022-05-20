package com.tq.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//@SpringBootApplication注解来标准一个主程序类，说明这是一个spring boot应用
@SpringBootApplication//里面有@SpringBootConfiguration @EnableAutoConfiguration里面有自动扫描包
public class Springboot01Application {

    public static void main(String[] args) {
        SpringApplication.run(Springboot01Application.class, args);
    }

}

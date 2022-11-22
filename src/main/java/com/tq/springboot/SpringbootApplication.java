package com.tq.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//@SpringBootApplication注解来标准一个主程序类，说明这是一个spring boot应用
@SpringBootApplication//里面有@SpringBootConfiguration @EnableAutoConfiguration里面有自动扫描包
@MapperScan("com.tq.springboot.mapper")
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

}

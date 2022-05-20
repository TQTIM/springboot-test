package com.tq.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//自己创建配置类来扩展springmvc自动配置功能，实现WebMvcConfigurer接口，重写相关方法
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override //重写其中的addViewControllers方法，让请求来到其他访问路径页面
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/ok").setViewName("/success");//访问/ok请求来到/success路径
    }
}

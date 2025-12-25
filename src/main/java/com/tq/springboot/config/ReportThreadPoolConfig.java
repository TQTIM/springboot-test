package com.tq.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * @author tq
 * @date 2025/12/25 17:03
 * @description: 报表线程池配置
 */
@Configuration
public class ReportThreadPoolConfig {
    @Bean(name = "reportExecutor",destroyMethod = "shutdown")// Spring会在容器关闭时帮你调用shutdown()
    public ThreadPoolExecutor reportExecutor(){
        //可改用配置文件传参方式
        return new ThreadPoolExecutor(3, 6, 60,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(50), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
}

package com.tq.springboot.juc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Thread2 implements CommandLineRunner {

    private final Thread1 thread1;

    public Thread2(Thread1 thread1) {
        this.thread1 = thread1;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("主线程开始：" + Thread.currentThread().getName());
        thread1.doSomething();  // 异步执行
        System.out.println("主线程结束：" + Thread.currentThread().getName());
    }
}

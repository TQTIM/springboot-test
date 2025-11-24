package com.tq.springboot.juc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AsyncTest2 implements CommandLineRunner {

    private final AsyncTest1 asyncTest1;

    public AsyncTest2(AsyncTest1 asyncTest1) {
        this.asyncTest1 = asyncTest1;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("主线程开始：" + Thread.currentThread().getName());
        asyncTest1.doSomething();  // 异步执行
        System.out.println("主线程结束：" + Thread.currentThread().getName());
    }
}

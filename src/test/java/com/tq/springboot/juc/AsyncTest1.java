package com.tq.springboot.juc;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class AsyncTest1 {
    @Async
    public  void doSomething() throws InterruptedException {
        System.out.println("A 开始");
        A();
        System.out.println("A 结束");
        B();
        System.out.println("B done");
    }

    public  void A() throws InterruptedException {
        int n =1;
        List list = new ArrayList<>();
        System.out.println("魂环开始时间"+LocalDateTime.now());
        for (int i = 0; i < 100000000; i++) {
            list.add(i);
        }
        System.out.println("魂环结束时间"+LocalDateTime.now());
        System.out.println("A 结束"+n );

    }

    public static void B() {
        System.out.println("B 开始");
        CompletableFuture.runAsync(() -> {
            System.out.println("B async running");
        });
    }

    public static void main(String[] args) throws InterruptedException {
        AsyncTest1 asyncTest1 = new AsyncTest1();
        asyncTest1.doSomething();
    }

}


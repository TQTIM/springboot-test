package com.tq.springboot.thread;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {


    @Override
    public Integer call() throws Exception {
        for (int i = 0; i < 10; i++) {
            System.out.println(System.currentTimeMillis() + "我是线程callable正在执行任务");
        }
        return 1;
    }

}

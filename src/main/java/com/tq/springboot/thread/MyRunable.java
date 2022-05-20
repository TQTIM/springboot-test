package com.tq.springboot.thread;

public class MyRunable implements Runnable {
    @Override
    public void run() {
        for(int i=0;i<10;i++){
            System.out.println(System.currentTimeMillis()+"我是线程runable正在执行任务");
        }
    }
}

package com.tq.springboot.thread;

public class MyThread extends Thread {

    @Override
    public void run(){
        for(int i=0;i<10;i++){
            System.out.println(System.currentTimeMillis()+"我是线程thread正在执行任务");
        }
    }
}

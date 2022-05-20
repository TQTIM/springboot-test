package com.tq.springboot.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class MyMain {


    public static void main(String[] args) {
        //testMyThread1();
        //testMyThread2();
        //testMyThread3();
        testMyThread4();
    }
    public static void testMyThread1(){
        MyThread myThread=new MyThread();
        myThread.start();
    }

    public static void testMyThread2(){
        MyRunable myRunable=new MyRunable();
        Thread thread=new Thread(myRunable);
        thread.start();
    }

    public static Integer testMyThread3(){
        MyCallable myCallable=new MyCallable();
        FutureTask<Integer> ft = new FutureTask<Integer>(myCallable);
        Thread thread=new Thread(ft);
        thread.start();
        return 1;
    }


    public static void testMyThread4() {
        ThreadPool threadPool=new ThreadPool();
        //单线程池
        ExecutorService executorService= Executors.newSingleThreadExecutor();
        //可定时的线程池
        ExecutorService executorService1=Executors.newSingleThreadScheduledExecutor();
        //可缓存的线程池
        ExecutorService executorService2=Executors.newCachedThreadPool();
        //定长线程池
        /*ExecutorService executorService3=Executors.newFixedThreadPool(10);
        executorService.execute(threadPool);
        System.out.println("**************************************");
        executorService1.execute(threadPool);
        System.out.println("**************************************");
        executorService2.execute(threadPool);
        System.out.println("**************************************");
        executorService3.execute(threadPool);*/

        executorService2.execute(new FinalThread("小淘","杭州"));


        ExecutorService executorService3 = Executors.newFixedThreadPool(5);
        executorService3.execute(new FinalThread("唐其","江西"));


    }


}

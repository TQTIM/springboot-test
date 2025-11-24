package com.tq.springboot.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @Auther: tq
 * @Date: 2023/2/17
 * @Description
 * @Version: 1.0
 */
public class Test {

    public static void main (String args[]) throws ExecutionException, InterruptedException {

        Map map = new HashMap<String,Object>();
        map.put("m","ok");
        HashSet<Object> set = new HashSet<>();
        set.add("setall");
        new Test().test();

        MyCallable myCallable = new MyCallable();
        FutureTask<String> futureTask = new FutureTask<>(myCallable);
        new Thread(futureTask).start();
        String s = futureTask.get();
        System.out.println(s);

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<String> submit = executorService.submit(()->"线程池submit返回callable");
        System.out.println(submit.get());
        executorService.shutdown();
    }
    public void test(){
        new MyThread().start();
    }

    class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println("继承的run");
        }
    }

}

class MyCallable implements Callable<String>{
    @Override
    public String call() throws Exception {
        return "这是callable";
    }
}
package com.tq.springboot.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author TQ
 * @version 1.0
 * @Description
 * @create 2021-12-14 16:16
 */
public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
        List<String> lists = new ArrayList<>();
        CopyOnWriteArrayList<String> clists = new CopyOnWriteArrayList<>();
        for (int j = 0; j < 1000; j++) {
            new Thread(()->{
                clists.add(Thread.currentThread().getName());
            }).start();
        }


        for (int i = 0; i < 1000; i++) {
            new Thread(()->{
                synchronized (lists){
                    lists.add(Thread.currentThread().getName());
                }

            }).start();

        }
        Thread.sleep(2000);
        System.out.println(clists.size());
        System.out.println(lists.size());
    }
}

package com.tq.springboot.juc;/*
package com.tq.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

*/

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author TQ
 * @version 1.0
 * @Description
 * @create 2021-12-14 11:05
 */

@SpringBootTest
public class UnsafeThread {

    @Test
    public void ThreadList() {
        List<String> lists = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            new Thread(()->{
                synchronized (lists){
                    lists.add(Thread.currentThread().getName());
                }

            }).start();

        }

        System.out.println(lists.size());
    }


    public static void main(String[] args) throws InterruptedException {
        List<String> lists = new ArrayList<>();
        CopyOnWriteArrayList<String> clists = new CopyOnWriteArrayList<>();
        for (int j = 0; j < 1000; j++) {
            new Thread(()->{
                clists.add(Thread.currentThread().getName());
            }).start();
        }
        System.out.println(clists.size());

        for (int i = 0; i < 1000; i++) {
            new Thread(()->{
                synchronized (lists){
                    lists.add(Thread.currentThread().getName());
                }

            }).start();

        }
        Thread.sleep(20000);
        System.out.println(lists.size());
    }

}

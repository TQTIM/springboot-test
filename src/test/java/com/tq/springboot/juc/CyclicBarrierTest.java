package com.tq.springboot.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    public static volatile int state =1;
    //9、使用CyclicBarrier保证顺序执行ABC ,写法不推荐CyclicBarrier 这里写的没有真正发挥作用，而且while自旋浪费cpu。试用于多组且每组任务没顺序并发执行任务，比如多个用户计算其每月电费并汇总
    public static void main(String[] args) {
        //可以传入Runable接口，当等待线程达到数量后先执行回调Runable接口
        CyclicBarrier cyclicBarrier = new CyclicBarrier(1);
        new Thread(()->{
            while (state !=1){

            }
            System.out.println("A");
            state =2;
        }).start();
        new Thread(()->{
            try {
                cyclicBarrier.await();
                while (state !=2){

                }
                System.out.println("B");
                state =3;
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(()->{
            try {
                cyclicBarrier.await();
                while (state !=3){

                }
                System.out.println("C");
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}

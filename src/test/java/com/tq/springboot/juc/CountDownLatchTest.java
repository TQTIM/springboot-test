package com.tq.springboot.juc;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        //7、公交车等人齐了司机才开车
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"上车");
                countDownLatch.countDown(); //数量-1
            }).start();
        }

        //阻塞，等计数器归0才继续执行
        countDownLatch.await();
        System.out.println("全部上车了，关门出发");

    }

}


class CountDownLatchTest2{
    //8、使用CountDownLatch实现按顺序打印字符串
    public static void main(String[] args) {
        CountDownLatch latchB = new CountDownLatch(1);
        CountDownLatch latchC = new CountDownLatch(1);

        new Thread(()->{
            System.out.println("A");
            latchB.countDown();
        }).start();
        new Thread(()->{
            try {
                latchB.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("B");
            latchC.countDown();
        }).start();
        new Thread(()->{
            try {
                latchC.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("C");
        }).start();

        //主线程可能先结束，用join或者整体再用个计数器可控制主线程最后结束
        System.out.println("结束！");
    }
}
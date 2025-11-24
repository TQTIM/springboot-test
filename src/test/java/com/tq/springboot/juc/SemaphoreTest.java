package com.tq.springboot.juc;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    //信号量，用于控制同时访问某个资源的线程数量。它底层依赖的是 AQS
       //限制数据库连接池中连接数量。
       //控制某段代码同一时刻只能被 N 个线程执行。
      // 实现对象池、限流等场景

    public static void main(String[] args) {
        // 6车---》3停车位置
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i <= 5; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire(); //获得，假设如果已经满了，等待，等待被释放为止！
                    System.out.println(Thread.currentThread().getName()+"抢到车位");
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName()+"离开车位");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }finally {
                    semaphore.release();//释放，会将当前的信号量释放 + 1，然后唤醒等待的线程！
                }
            },"车辆"+i).start();
        }
    }
}

class SemaphoreTest2{
    //10、使用Semaphore顺序打印abc  （可以用3个控制，也可以1个加状态值但好像没那么好些不如用3个）
    public static void main(String[] args) {
        Semaphore semaphore1 = new Semaphore(1);
        Semaphore semaphore2 = new Semaphore(0);
        Semaphore semaphore3 = new Semaphore(0);

        new Thread(()->{
                try {
                    semaphore1.acquire();
                    System.out.println("A");
                    semaphore2.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
        }).start();
        new Thread(()->{
                try {
                    semaphore2.acquire();
                    System.out.println("B");
                    semaphore3.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
            }
        }).start();
        new Thread(()->{
                try {
                    semaphore3.acquire();
                    System.out.println("C");
                //    semaphore1.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
        }).start();
    }
}

class SemaphoreTest3{
    //11、使用Semaphore顺序循环打印abc
    public static void main(String[] args) {
        Semaphore semaphore1 = new Semaphore(1);
        Semaphore semaphore2 = new Semaphore(0);
        Semaphore semaphore3 = new Semaphore(0);

        new Thread(()->{
            while (true){
                try {
                    semaphore1.acquire();
                    System.out.println("A");
                    semaphore2.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
        new Thread(()->{
            while (true){
                try {
                    semaphore2.acquire();
                    System.out.println("B");
                    semaphore3.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
        new Thread(()->{
            while (true){
                try {
                    semaphore3.acquire();
                    System.out.println("C");
                    semaphore1.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

}
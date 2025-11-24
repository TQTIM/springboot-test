package com.tq.springboot.juc;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueTest {
    //1、BlockingQueue阻塞队列，是一种线程安全的阻塞队列，常用于 生产者-消费者模型，可用于多线程并发处理，线程池！ 实现类有 ArrayBlockingQueue、 LinkedBlockingQueue

    /**
     * 抛出异常
     */
    @Test
    public  void test1() {
        // 队列的大小
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        // IllegalStateException: Queue full 抛出异常！
// System.out.println(blockingQueue.add("d"));
        System.out.println("=-===========");
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        // java.util.NoSuchElementException 抛出异常！
//        System.out.println(blockingQueue.remove());
    }

    /**
     * 有返回值，没有异常
     */
    @Test
    public  void test2() {
        // 队列的大小
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        // System.out.println(blockingQueue.offer("d")); // false 不抛出异常！
        System.out.println("============================");
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll()); //获取并移除元素， 队列空返回null 不抛出异常！
    }

    /**
     * 等待，阻塞（一直阻塞）
     */
    @Test
    public  void test3() throws InterruptedException {
        // 队列的大小
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
        // 一直阻塞
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
       // blockingQueue.put("d"); // 队列没有位置了，一直阻塞
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
    }

    /**
     * 等待，阻塞（等待超时）
     */
    @Test
    public  void test4() throws InterruptedException {
        // 队列的大小
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);

        blockingQueue.offer("a");
        blockingQueue.offer("b");
        blockingQueue.offer("c");
      //   blockingQueue.offer("d",2,TimeUnit.SECONDS); // 等待超过2秒就退出
        System.out.println("===============");
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        blockingQueue.poll(2, TimeUnit.SECONDS); // 等待超过2秒就退出
    }


    //2、SynchronousQueue 同步队列是是 BlockingQueue 接口的一个特殊实现，它没有内部容量，每个插入操作必须等待一个相应的移除操作，反之亦然
    //是一种零容量的直接交换队列，适用于线程之间同步交接任务，或者线程池中无需排队的任务立即处理模式，但因为其行为极端，需要谨慎使用
    /**
     * 同步队列
     * 和其他的BlockingQueue 不一样， SynchronousQueue 不存储元素
     * put了一个元素，必须从里面先take取出来，否则不能在put进去值！
     * 可以理解为：一个线程把东西递给另一个线程，必须手递手、你接我放
     */
        public static void main(String[] args) {
            BlockingQueue<String> blockingQueue = new SynchronousQueue<>(); // 同步队列
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName()+" put 1");
                    blockingQueue.put("1");
                    System.out.println(Thread.currentThread().getName()+" put 2");
                    blockingQueue.put("2");
                    System.out.println(Thread.currentThread().getName()+" put 3");
                    blockingQueue.put("3");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"T1").start();
            new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(3);

                    System.out.println(Thread.currentThread().getName()+"=>"+blockingQueue.take());
                    TimeUnit.SECONDS.sleep(3);

                    System.out.println(Thread.currentThread().getName()+"=>"+blockingQueue.take());
                    TimeUnit.SECONDS.sleep(3);

                    System.out.println(Thread.currentThread().getName()+"=>"+blockingQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"T2").start();
        }



}


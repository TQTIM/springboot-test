package com.tq.springboot.juc;

import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest {

    @Test
    public  void Syc() {
        Deal deal = new Deal();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    deal.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    deal.decrement();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    deal.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        },"C").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    deal.decrement();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        },"D").start();
    }

    @Test
    public  void Condition() {
        Deal2 deal2 = new Deal2();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    deal2.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    deal2.decrement();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    deal2.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        },"C").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    deal2.decrement();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        },"D").start();
    }

    //1、线程生产消费问题 Synchronized版
     class Deal{
        private int num =0;
        //生产+1
        public synchronized void increment() throws InterruptedException {
            //用while不用if是为了防止虚假唤醒。while当线程从wait()返回时，会重新检查条件
            while (num !=0){
                this.wait();
            }
            num++;
            System.out.println(Thread.currentThread().getName()+"==>"+num);
            this.notifyAll();
        }

        //消费-1
        public synchronized void decrement() throws InterruptedException {
            while (num ==0){
                this.wait();
            }
            num--;
            System.out.println(Thread.currentThread().getName()+"==>"+num);
            this.notifyAll();
        }
    }

    //2、线程生产消费问题 Lock conditon条件版
        class Deal2{
        private int num = 0;
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        public void increment() throws InterruptedException {
            lock.lock();
            try {
                while (num !=0){
                    condition.await();
                }
                num ++;
                System.out.println(Thread.currentThread().getName()+"==>"+num);
                condition.signalAll();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

        }

        public void decrement() throws InterruptedException {
            lock.lock();
            try {
                while (num ==0){
                    condition.await();
                }
                num --;
                System.out.println(Thread.currentThread().getName()+"==>"+num);
                condition.signalAll();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }

    //3、任务按顺序执行join版
    @Test
    public void ThreadJo() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("任务1执行");
        });
        Thread t2= new Thread(() -> {
            try {
           //     t1.join();
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("任务2执行");
        });
        Thread t3 = new Thread(() -> {
            try {
            //    t2.join();
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("任务3执行");
        });

        t1.start();
        t1.join();
        t2.start();
        t2.join();
        t3.start();
        t3.join();
    //    System.out.println("结束！");
    }


    private static Lock lock = new ReentrantLock();
    private static Condition condition1 = lock.newCondition();
    private static Condition condition2 = lock.newCondition();
    private static Condition condition3 = lock.newCondition();
    private static volatile int state = 1;
    //4、任务按顺序执行condition版 A->B->C (其实两个condition就够了)
    @Test
    public void ThreadCondi(){
        new Thread(()->{
            lock.lock();
            try {
            while (state !=1){
                    condition1.await();

            }
            System.out.println("任务1执行");
            state =2;
            condition2.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }finally {
                lock.unlock();
            }
        }).start();

        new Thread(()->{
            lock.lock();
            try {
                while (state !=2){
                    condition2.await();

                }
                System.out.println("任务2执行");
                state =3;
                condition3.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }finally {
                lock.unlock();
            }
        }).start();

        new Thread(()->{
            lock.lock();
            try {
                while (state !=3){
                    condition3.await();

                }
                System.out.println("任务3执行");
                state =1;
                condition1.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }finally {
                lock.unlock();
            }
        }).start();

    }

    //5、任务按顺序循环执行Lock+ condition版 A->B->C ->A..，不用junit因为主线程可能在子线程执行前就退出了，从而导致程序提前结束或无法看到输出
    public static void main(String[] args) {
        new Thread(()->{
            while (true){
                lock.lock();
                try {
                    while (state !=1){
                        condition1.await();

                    }
                    Thread.sleep(500);
                    System.out.println("任务1执行");
                    state =2;
                    condition2.signal();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }finally {
                    lock.unlock();
                }
            }

        }).start();

        new Thread(()->{
            while (true){
                lock.lock();
                try {
                    while (state !=2){
                        condition2.await();

                    }
                    Thread.sleep(500);
                    System.out.println("任务2执行");
                    state =3;
                    condition3.signal();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }finally {
                    lock.unlock();
                }
            }

        }).start();

        new Thread(()->{
            while (true){
                lock.lock();
                try {
                    while (state !=3){
                        condition3.await();

                    }
                    Thread.sleep(500);
                    System.out.println("任务3执行");
                    state =1;
                    condition1.signal();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }finally {
                    lock.unlock();
                }
            }

        }).start();

      //  System.out.println("结束！");
    }





}

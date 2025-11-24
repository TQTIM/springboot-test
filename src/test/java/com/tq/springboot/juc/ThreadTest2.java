package com.tq.springboot.juc;

public class ThreadTest2 {
    private static volatile int flag =1;
    private static final Object obj =new Object();

    public static void main(String[] args) {
        //6、使用synchronized + wait/notify循环打印字符串
        new Thread(()->{
            try {
                synchronized (obj){
                    while (true){
                        while (flag !=1){
                            obj.wait();
                        }
                        System.out.println("A");
                        flag = 2;
                        obj.notifyAll();
                    }

                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"线程A").start();
        new Thread(()->{
            try {
                synchronized (obj){
                    while (true){
                        while (flag !=2){
                            obj.wait();
                        }
                        System.out.println("B");
                        flag = 3;
                        obj.notifyAll();
                    }
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"线程B").start();

        new Thread(()->{
            synchronized (obj){
                while (true){
                    while (flag !=3){
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("C");
                    flag = 1;
                    obj.notifyAll();
                }
            }
        },"线程C").start();
    }



}

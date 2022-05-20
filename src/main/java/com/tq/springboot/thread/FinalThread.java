package com.tq.springboot.thread;

public class FinalThread implements Runnable {

    private String name;
    private String address;

    public FinalThread(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public void run() {
        getData();
    }

    public void getData(){
        for(int i=0;i<10000;i++){
            System.out.println(Thread.currentThread().getName()+"---->名字为:"+name+"地址为:"+address);
        }
    }

}

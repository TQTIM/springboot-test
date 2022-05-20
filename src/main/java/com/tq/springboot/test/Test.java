package com.tq.springboot.test;

public class Test {//递归
    public static void main(String[] args) {
        String bizId="lejifa";
        int n=9;
        getTextHash(bizId,n);

    }
    public static void getTextHash(String bizId,int n){
        System.out.println("方法调用次数--"+bizId+"--->"+n);
        if (n==0) {
            return;
        }else {
            getTextHash(bizId,n-1);
        }


    }
}

package com.tq.springboot.utils;

/**
 * @Auther: tq
 * @Date: 2023/2/17
 * @Description
 * @Version: 1.0
 */
public class Test {
    public  int aMethod() {
         int i = 0;
        i++;
        return i;
    }
    public static void main (String args[]) {
        Test test = new Test();
        test.aMethod();
        int j = test.aMethod();
        System.out.println(j);
    }
}

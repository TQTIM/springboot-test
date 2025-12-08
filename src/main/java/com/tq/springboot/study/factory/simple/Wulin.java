package com.tq.springboot.study.factory.simple;

/**
 * @author tq
 * @date 2025/11/18 16:20
 * @description: 五菱
 */
public class Wulin implements Car{
    @Override
    public void name() {
        System.out.println("五菱");
    }
}

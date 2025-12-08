package com.tq.springboot.study.factory.simple;

/**
 * @author tq
 * @date 2025/11/18 16:23
 * @description: 特斯拉
 */
public class Tesla implements Car{
    @Override
    public void name() {
        System.out.println("特斯拉");
    }
}

package com.tq.springboot.study.factory.method;

/**
 * @author tq
 * @date 2025/11/18 16:36
 * @description: 消费者
 * 工厂方法好处是如果要拓展新的车，只要实现Car和CarFactory接口就行，不需要改Car和CarFactory代码。坏处是代码量多了每次得创建新的
 */
public class Consumer {
    public static void main(String[] args) {
        Car car1 = new WulinFactory().getCar();
        Car car2 = new TeslaFactory().getCar();

        car1.name();
        car2.name();
    }
}

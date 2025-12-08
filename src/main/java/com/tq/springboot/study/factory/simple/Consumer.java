package com.tq.springboot.study.factory.simple;

/**
 * @author tq
 * @date 2025/11/18 16:43
 * @description: 消费者
 */
public class Consumer {
    public static void main(String[] args) {
        Car car1 = CarFactory.getCar("五菱");
        Car car2 = CarFactory.getCar("特斯拉");

        car1.name();
        car2.name();
    }
}

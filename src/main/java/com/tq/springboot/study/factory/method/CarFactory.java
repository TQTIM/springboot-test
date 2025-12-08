package com.tq.springboot.study.factory.method;

/**
 * @author tq
 * @date 2025/11/18 16:16
 * @description: 工厂方法模式，对每一种车都对应一种工厂
 *
 */
public interface CarFactory {
    Car getCar(); //规范工厂
}

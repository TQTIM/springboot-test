package com.tq.springboot.study.factory.method;

/**
 * @author tq
 * @date 2025/11/18 16:34
 * @description: 特斯拉工厂
 */
public class TeslaFactory implements CarFactory {
    @Override
    public Car getCar() {
        return new Tesla();
    }
}

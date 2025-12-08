package com.tq.springboot.study.factory.method;

/**
 * @author tq
 * @date 2025/11/18 16:34
 * @description: 五菱工厂
 */
public class WulinFactory implements CarFactory {
    @Override
    public Car getCar() {
        return new Wulin();
    }
}

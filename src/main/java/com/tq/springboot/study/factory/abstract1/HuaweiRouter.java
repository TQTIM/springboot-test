package com.tq.springboot.study.factory.abstract1;

/**
 * @author tq
 * @date 2025/11/18 17:22
 * @description: 华为路由器
 */
public class HuaweiRouter implements RouterProduct {
    @Override
    public void openWifi() {
        System.out.println("华为路由器打开wifi");
    }

    @Override
    public void setting() {
        System.out.println("华为路由器设置");
    }

    @Override
    public void start() {
        System.out.println("华为路由器启动");
    }
}

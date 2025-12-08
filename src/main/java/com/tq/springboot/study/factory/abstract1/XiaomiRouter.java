package com.tq.springboot.study.factory.abstract1;

/**
 * @author tq
 * @date 2025/11/18 17:22
 * @description: 小米路由器
 */
public class XiaomiRouter  implements RouterProduct {
    @Override
    public void openWifi() {
        System.out.println("小米路由器打开wifi");
    }

    @Override
    public void setting() {
        System.out.println("小米路由器设置");
    }

    @Override
    public void start() {
        System.out.println("小米路由器启动");
    }
}

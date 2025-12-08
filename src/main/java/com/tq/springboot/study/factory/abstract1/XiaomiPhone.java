package com.tq.springboot.study.factory.abstract1;

/**
 * @author tq
 * @date 2025/11/18 17:12
 * @description: 小米手机
 */
public class XiaomiPhone implements PhoneProduct{
    @Override
    public void start() {
        System.out.println("小米手机启动");
    }

    @Override
    public void shutdown() {
        System.out.println("小米手机关机");
    }

    @Override
    public void sendSms() {
        System.out.println("小米手机发短信");
    }

    @Override
    public void callup() {
        System.out.println("小米手机打电话");
    }
}

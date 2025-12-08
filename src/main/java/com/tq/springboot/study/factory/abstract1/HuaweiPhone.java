package com.tq.springboot.study.factory.abstract1;

/**
 * @author tq
 * @date 2025/11/18 17:12
 * @description: 华为手机
 */
public class HuaweiPhone implements PhoneProduct{
    @Override
    public void start() {
        System.out.println("华为手机启动");
    }

    @Override
    public void shutdown() {
        System.out.println("华为手机关机");
    }

    @Override
    public void sendSms() {
        System.out.println("华为手机发短信");
    }

    @Override
    public void callup() {
        System.out.println("华为手机打电话");
    }
}

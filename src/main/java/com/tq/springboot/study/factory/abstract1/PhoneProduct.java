package com.tq.springboot.study.factory.abstract1;

/**
 * @author tq
 * @date 2025/11/18 17:14
 * @description: 手机产品接口
 */
public interface PhoneProduct {
    void start();
    void shutdown();
    void sendSms();
    void callup();
}

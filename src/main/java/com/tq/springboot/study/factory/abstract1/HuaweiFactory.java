package com.tq.springboot.study.factory.abstract1;

/**
 * @author tq
 * @date 2025/11/18 17:32
 * @description: 华为工厂
 */
public class HuaweiFactory implements ProductFactory {
    @Override
    public PhoneProduct myphoneProduct() {
        return new HuaweiPhone(); //华为工厂做华为手机
    }

    @Override
    public RouterProduct myRouterProduct() {
        return new HuaweiRouter();//华为工厂做华为路由器
    }
}

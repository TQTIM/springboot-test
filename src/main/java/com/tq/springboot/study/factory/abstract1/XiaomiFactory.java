package com.tq.springboot.study.factory.abstract1;

/**
 * @author tq
 * @date 2025/11/18 17:32
 * @description: 小米工厂
 */
public class XiaomiFactory implements ProductFactory {
    @Override
    public PhoneProduct myphoneProduct() {
        return new XiaomiPhone(); //小米工厂做小米手机
    }

    @Override
    public RouterProduct myRouterProduct() {
        return new XiaomiRouter();//小米工厂做小米路由器
    }
}

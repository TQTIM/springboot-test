package com.tq.springboot.study.factory.abstract1;

/**
 * @author tq
 * @date 2025/11/18 17:26
 * @description: 抽象工厂模式
 * 抽象产品工厂，同一个厂家下面有不同产品，不同厂家实现这个接口就能做不同的产品
 */
public interface ProductFactory {
    //生产手机
    PhoneProduct myphoneProduct();
    //生产路由器
    RouterProduct myRouterProduct();
}

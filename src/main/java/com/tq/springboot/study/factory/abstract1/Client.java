package com.tq.springboot.study.factory.abstract1;

/**
 * @author tq
 * @date 2025/11/18 17:39
 * @description: 抽象工厂模式，相当于套娃加了一层
 * 优点不用关心具体工厂实现细节，产品家族统一，将一个系列产品统一到一起创建，客户端与具体类解耦。缺点：难以支持新的产品种类
 * 比如win和mac需要不同风格的按钮和文本框、Java程序需要连接不同的数据库（MySQL, Oracle, PostgreSQL等）
 */
public class Client {
     public static void main(String[] args) {
          System.out.println("---------小米系列产品---------");
          XiaomiFactory xiaomiFactory = new XiaomiFactory();  //这里也可以通过关键词匹配哪种工厂new哪个工厂
          PhoneProduct phoneProduct = xiaomiFactory.myphoneProduct();
          phoneProduct.start();
          phoneProduct.callup();
          phoneProduct.sendSms();

          RouterProduct routerProduct = xiaomiFactory.myRouterProduct();
          routerProduct.openWifi();
          routerProduct.setting();


          System.out.println("---------华为系列产品---------");
          HuaweiFactory huaweiFactory = new HuaweiFactory();
          phoneProduct = huaweiFactory.myphoneProduct();
          phoneProduct.sendSms();
          phoneProduct.callup();

          routerProduct = huaweiFactory.myRouterProduct();
          routerProduct.setting();
          routerProduct.openWifi();
     }
}

package com.tq.springboot.study.factory.simple;

/**
 * @author tq
 * @date 2025/11/18 16:16
 * @description: 简单工厂模式 （静态工厂）
 * 这种方式如果有新的车则需要改动工厂代码
 */
public class CarFactory {
    //方式一：工厂写一起
    public static Car getCar(String car){
        if(car.equals("五菱")){
            return new Wulin();
        }else if (car.equals("特斯拉")){
            return new Tesla();
        }else{
            return null;
        }
    }


    //方式二：多个工厂方法分开写
    public static Car getWulin(){
        return new Wulin();
    }
}

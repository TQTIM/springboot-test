package com.tq.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: tq
 * @Date: 2022/6/16
 * @Description
 * @Version: 1.0
 */
@SpringBootTest
public class IteratorTest {

    @Test
    public void test1(){
        //一个List集合的值控制方法调用和结果，比如有1调用频次1方法，有3调用频次3方法，方法参数列表是上一个方法的执行结果
        List<String> rules =new ArrayList<>();
        rules.add("1");
        rules.add("2");
        rules.add("4");

        String resultLists="最初数据";
        String list="最初数据";
        for (int i = 0; i < rules.size(); i++) {
            switch (rules.get(i)) {
                case "1":
                     list = firstFrequency(list);
                    break;
                case "2":
                     list = secondFrequency(list);
                    break;
                case "3":
                    list = thirdFrequency(list);
                    break;
                case "4":
                    list = fourthFrequency(list);
                    break;
                default:
                    break;
            }
        }
            System.out.println("---结束:"+list);


        /* 这样写出问题了，得final修饰？？
        rules.forEach(
                e->{
                    String lists=null;
                    switch (e){
                        case "1":
                            lists = firstFrequency(resultLists);
                            break;
                        case "2":
                            secondFrequency(resultLists);
                            break;
                        case "3":
                            thirdFrequency(resultLists);
                            break;
                        case "4":
                            fourthFrequency(resultLists);
                            break;
                    }
                }
        );*/





    }

    public String firstFrequency(String s){
        System.out.println("====执行规则1方法,参数是："+s);
        return "规则1后的数据";
    }
    public String secondFrequency(String s){
        System.out.println("====执行规则2方法,参数是:"+s);
        return "规则2后的数据";
    }
    public String thirdFrequency(String s){
        System.out.println("====执行规则3方法,参数是:"+s);
        return "规则3后的数据";
    }
    public String fourthFrequency(String s){
        System.out.println("====执行规则4方法,参数是"+s);
        return "规则4后的数据";
    }
}

package com.tq.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Auther: tq
 * @Date: 2022/4/15
 * @Description
 * @Version: 1.0
 */
@SpringBootTest
public class SubStringTest {
    @Test
    public void subString(){
        String filePath="/opt/linkivr/recfile/20220102/13262331169_18058366182_20220102101414.wav";
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
        String sqnumId="20211109115140302529011192";
        String recordName=sqnumId.substring(0,10);
        String name=filePath.substring(filePath.lastIndexOf("."));
        System.out.println(fileName);
        System.out.println("===》"+recordName);
        System.out.println(name);

    }

    @Test
    public void subString2() {
        String s="4769702248455916#20220317122126再次外呼";
        String ss="476970224845591620220317122126再次外呼";
        String s1 = s.substring(s.lastIndexOf("#"));
        //字符串截取
        String s2=s.substring(0,s.indexOf("#"));
        //字符串是否包含某个字符
        int i = s.indexOf("#");
        System.out.println(s.contains("#"));
        System.out.println(ss.contains("#"));

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(i);

    }
    @Test
    public void subString3() {
        //将字符串以分隔符分割
        String s3="产品2#产品3#产品4";
        String[] split = s3.split("#");
        for (String s : split) {
            System.out.println(s);
        }
    }

    //字符串包含某个字符串
    @Test
    public void contains(){
        String s ="kw_work_order_999_577";
        boolean b = s.contains("kw_work_order");
        boolean b2 = s.contains("kw_work_order_999");
        System.out.println(b+"==="+b2);
        if(!b || b2){
            System.out.println("需要被排除!!!");
        }
    }

}

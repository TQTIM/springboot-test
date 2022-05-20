package com.tq.springboot.test;

import com.tq.springboot.entity.ClassJson;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: tq
 * @Date: 2022/1/12
 * @Description
 * @Version: 1.0
 */
public class ListTest {
    public static void main(String[] args) {
        List<ClassJson> tlist = new ArrayList<ClassJson>();
        ClassJson classJson = new ClassJson();
        classJson.setCode("001");
        classJson.setText("北京市");
        tlist.add(classJson);
        ClassJson classJson2 = new ClassJson();
        classJson2.setCode("002");
        classJson2.setText("天津市");
        tlist.add(classJson2);

        for (ClassJson str :
                tlist) {
            System.out.println(str);
        }
        for (int i = 0; i < tlist.size(); i++) {
            System.out.println(tlist.get(i));
        }
        //可以重写classjson中tostring()方法打印结果
        tlist.forEach(item-> System.out.println(item));
        System.out.println(tlist);

    }
}

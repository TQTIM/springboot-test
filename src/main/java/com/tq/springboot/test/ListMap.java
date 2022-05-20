package com.tq.springboot.test;

import java.util.*;

/**
 * @Auther: tq
 * @Date: 2022/3/20
 * @Description
 * @Version: 1.0
 */
public class ListMap {
    void contextLoads() {
        Map<String,Object> res=new HashMap<>();
        Map<String,Object> res2=new HashMap<>();
        List<Map<String ,Object>> listMaps=new ArrayList<Map<String, Object>>();

        res.put("code", 1000);
        res.put("success", true);
        res.put("message", "查询成功");
        //res.put("data", listMaps);

        res2.put("code",404);
        res2.put("erro",false);
        res2.put("message","查询失败");
        listMaps.add(res);
        listMaps.add(res2);
        System.out.println(listMaps.toString());
        System.out.println(res);
//遍历map三种方式
        for (String key :
                res.keySet()) {
            System.out.println("key:"+key+ "---->value:"+res.get(key));

        }
        for (Map.Entry<String, Object> entry :
                res.entrySet()) {
            System.out.println(entry.getKey()+"--->"+entry.getValue());
        }

        Iterator<Map.Entry<String, Object>> it = res.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            System.out.println(entry.getKey()+"---"+entry.getValue());
        }



        //遍历list里的map
        for (Map<String,Object> listMap :
                listMaps) {
            for (String key :
                    listMap.keySet()) {
                System.out.println("key:"+key+ "---->value:"+listMap.get(key));

            }

        }

    }
}

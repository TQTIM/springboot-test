package com.tq.springboot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Auther: tq
 * @Date: 2022/4/25
 * @Description
 * @Version: 1.0
 */
public class Stream {
    @Test
    public void mapToList() { //将map满足条件value 的key转成list
        Map<String, String> failMaps = new HashMap<>();
        failMaps.put("1234", "0");
        failMaps.put("12345", "1");
        failMaps.put("123456", "0");

        //获取redis中符合条件的需再次同步的推送失败的数据
        List<String> lists = failMaps.entrySet().stream().filter(f -> {
            return "0".equals(f.getValue());
        }).map(Map.Entry::getKey).collect(Collectors.toList());

        lists.forEach(System.out::println);


    }
    @Test
    public void listMap(){
        List<Map<String,String>> failLists = new ArrayList<>();
        Map<String, String> failMaps = new HashMap<>();
        failMaps.put("sequnceId","123");
        failMaps.put("sfStatus","1");
        failMaps.put("isResend","0");
        failLists.add(failMaps);

        Map<String, String> failMaps2 = new HashMap<>();
        failMaps2.put("sequnceId","1234");
        failMaps2.put("sfStatus","0");
        failMaps2.put("isResend","0");
        failLists.add(failMaps2);

        Map<String, String> failMaps3 = new HashMap<>();
        failMaps3.put("sequnceId","12345");
        failMaps3.put("sfStatus","1");
        failMaps3.put("isResend","1");
        failLists.add(failMaps3);

        Map<String, String> failMaps4 = new HashMap<>();
        failMaps4.put("sequnceId","123456");
        failMaps4.put("sfStatus","0");
        failMaps4.put("isResend","1");
        failLists.add(failMaps4);

        //过滤
       /* if(failLists!=null && failLists.size()>0){
            List<Map<String, String>> list = failLists.stream().filter(f -> {
                return "0".equals(f.get("isResend"));
            }).collect(Collectors.toList());

            list.forEach(System.out::println);
        }*/

        //过滤匹配并修改List<Map<>>中map的值
        List<String> succResults =new ArrayList<>();
        succResults.add("1234");
        succResults.add("123456");

        List<Map<String, String>> callResultResends = failLists.stream().filter(f -> {
            for (String succResult : succResults) {
                if (succResult.equals(f.get("sequnceId"))) {
                    return false;
                }
            }
            return true;
        }).map(f->{
            int isResend = Integer.parseInt(f.get("isResend"));
            isResend++;
            f.put("isResend",String.valueOf(isResend));
            return f;
        }).collect(Collectors.toList());

        callResultResends.forEach(System.out::println);




    }
}
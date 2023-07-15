package com.tq.springboot;

import com.tq.springboot.entity.Code;
import org.junit.jupiter.api.Test;

import java.util.*;
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

    @Test
    public void streamTest(){
        List<Code> list1=new ArrayList<>();
        list1.add(new Code("1","10"));
        list1.add(new Code("2","20"));
        list1.add(new Code("3","30"));
        list1.add(new Code("4","40"));
        List<Code> list2=new ArrayList<>();
        list2.add(new Code(null,"10"));
        list2.add(new Code(null,"21"));
        list2.add(new Code(null,"40"));
        /*list1.forEach(o->{
            list2.stream().filter(s->!o.getName().equals(s.getName())).collect(Collectors.toList());
        });*/

        //方法引用
        /*Comparator<Integer> com=(x,y)->Integer.compare(x,y);//1、调用方法参数列表和返回值 和 抽象方法函数列表和返回值一致 可以用方法引用
        Comparator<Integer> com2= Integer::compare;
        BiPredicate<String,String> bp=(x,y)->x.equals(y);//2、参数列表第一个参数是实例方法调用者，而第二个参数是实例方法的参数时，可用ClassName::method
        BiPredicate<String,String> bp2= String::equals;*/
        //一个List测试
        list1.stream().filter(x->x.getName().equals("30")).collect(Collectors.toList());
        //list1.stream().filter(Integer.parseInt(x->x.getName())>30).collect(Collectors.toList());错误写法
        list1.stream().filter(x->Integer.parseInt(x.getName())>30).collect(Collectors.toList());

        // 两个集合交集====
        //第一种方式
        List<Code> list3 = list1.stream().filter(a -> {
            for (Code b : list2) {
                if (b.getName().equals(a.getName())) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());
        list3.forEach(System.out::println);
        System.out.println("------------");

        //第二种方式
        Map<String, String> list2Map = list2.stream().collect(Collectors.toMap(Code::getName, Code::getName));//先转map,这里key-value给他相同
        List<Code> twoResults = list1.stream()
                .filter(c -> list2Map.get(c.getName()) != null)
                .collect(Collectors.toList());//过滤出 List1的name能在list2变的map中找到值 的List1数据
        twoResults.forEach(System.out::println);

        //第三种
        //org.apache.commons.collections.CollectionUtils.subtract方法 ，这没导包
        //得重写hashcode和equals，
       /* Collection<String> subtractList = ListUtils.subtract(aList, bList);
        Collection<String> subtractList = CollectionUtils.subtract(aList, bList);
        list1.removeAll(list2) 这个也要重写hashcode 和equels*/

        //第四种
        List<Code> l1=new ArrayList<>();
        l1.add(new Code("1","110","产品1#产品2"));
        l1.add(new Code("2","220","产品2#产品3"));
        l1.add(new Code("3","110","产品2#产品3"));
        l1.add(new Code("4","220","产品2#产品3#产品4"));
        l1.add(new Code("5","130","产品2#产品3#产品4"));
        l1.add(new Code("6","110","产品1"));

        List<Code> l2=new ArrayList<>();
        l2.add(new Code(null,"110","产品1"));
        l2.add(new Code(null,"220","产品3"));
        l2.add(new Code(null,"440","产品1"));

        List<Code> updateLists1=new ArrayList<>();
        List<Code> results=new ArrayList<>();
        List<String> updateLists = l1.stream().filter(item -> {
            for (Code e : l2) {
                if (e.getName().equals(item.getName()) && Arrays.asList(item.getProdId().split("#")).contains(e.getProdId())) {
                    updateLists1.add(item);
                    return true;
                }
            }
            results.add(item);
            return false;
        }).map(Code::getCode).collect(Collectors.toList());

        System.out.println("======第四种====");
        updateLists.forEach(System.out::println);
        System.out.println("======交集====");
        updateLists1.forEach(System.out::println);
        System.out.println("======非交集====");
        results.forEach(System.out::println);


        //第五种
        List<Code> updateLists2=new ArrayList<>();
        l1.forEach(
                item->l2.forEach(e->{
                    if (e.getName().equals(item.getName())) {
                        updateLists2.add(item);
                    }
                })
        );
        System.out.println("====第五种");
        updateLists2.forEach(System.out::println);

        // 第六种
       /*
       l1.stream().filter(item->
                {l2.stream().map(e->e.getName()).collect(Collectors.toList()).contains(item.getName())
                    //  && l2.stream().map(e->e.getProdId())
                }
        )*/

        List<String> ss1 =new ArrayList<>();
        ss1.add("1");
        ss1.add("2");
        ss1.add("3");
        List<String> ss2 =new ArrayList<>();
        ss2.add("1");
        ss2.add("2");
        ss2.add("5");
        //两个list<String> 当ss2有"1"并且ss1有"3"，排除ss2的"1"
        ss2 = ss2.stream()
                .filter(e-> !("1".equals(e) && ss1.contains("3")))
                .collect(Collectors.toList());
        System.out.println("=====第六种（其他的）");
        System.out.println(ss1.contains("3"));
        System.out.println(ss2);
        ss2.forEach(System.out::println);

    }

    /**
     *  组织层级找最大的
     */
    @Test
    public void min(){
        List<String> list = new ArrayList<>();
        list.add("/1/22/333");
        list.add("/1/22");
        list.add("/1/22/33/44");
       // list.add("/1/33");
        list.add("/1/33/55");

        String shortest = Collections.min(list, Comparator.comparing(s -> s.split("/").length));
        int minLevel = shortest.split("/").length;

        List<String> result = new ArrayList<>();
        for (String s : list) {
            if (s.split("/").length == minLevel) {
                result.add(s);
            }
        }

        System.out.println(result);
    }

    @Test
    public void min2(){
        List<String> list = new ArrayList<>();
        list.add("/1/22/333");
        list.add("/1/22");
        list.add("/1/22/33/44");
        list.add("/1/33/55");
        list.add("/1/33/55/66");
        list.add("/2/22/333");
        list.add("/2/22");
        list.add("/2/22/33/44");
        list.add("/2/33/55");
        list.add("/2/33/55/66");

        Map<String, List<String>> map = new HashMap<>();
        for (String s : list) {
            String[] arr = s.split("/");
            if (arr.length >= 3) {
                String key = arr[1];
                String value = arr[2];
                if (value.equals("22") || value.equals("33")) {
                    if (!map.containsKey(key)) {
                        map.put(key, new ArrayList<>());
                    }
                    map.get(key).add(s);
                }
            }
        }

        List<String> result = new ArrayList<>();
        for (List<String> l : map.values()) {
            String min = l.get(0);
            for (String s : l) {
                if (s.split("/").length < min.split("/").length) {
                    min = s;
                }
            }
            result.add(min);
        }

        System.out.println(result);
    }
}

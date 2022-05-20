package com.tq.springboot.stream;

import com.tq.springboot.entity.Code;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.util.ListUtils;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

/**
 * @Auther: tq
 * @Date: 2022/3/21
 * @Description
 * @Version: 1.0
 */
public class StreamList {
    public static void main(String[] args) {
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

    }
}

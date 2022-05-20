package com.tq.springboot.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Auther: tq
 * @Date: 2022/1/20
 * @Description 排序Comparator
 * @Version: 1.0
 */
public class ComparatorTest {
    class dog{
        public int age;
        public String name;

        public dog(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return "dog{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }

    }

    public static void main(String[] args) {
        List<dog> lists = new ArrayList<>();
        lists.add(new ComparatorTest().new dog(4,"dogA"));
        lists.add(new ComparatorTest().new dog(2,"dogB"));
        lists.add(new ComparatorTest().new dog(7,"dogC"));
        lists.add(new ComparatorTest().new dog(5,"dogD"));
        lists.add(new ComparatorTest().new dog(1,"dogE"));
        System.out.println("未排序："+lists);
        for (ComparatorTest.dog list:lists
             ) {
            System.out.println("未排序前遍历："+list);
        }

        Collections.sort(lists, new Comparator<dog>() {
            @Override
            public int compare(dog o1, dog o2) {
                return o1.age-o2.age;
            }
        });
        System.out.println("按年龄排序："+lists);

        Collections.sort(lists, new Comparator<dog>() {
            @Override
            public int compare(dog o1, dog o2) {
                return 0;
            }
        });
    }
}

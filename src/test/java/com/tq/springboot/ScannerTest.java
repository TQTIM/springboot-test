package com.tq.springboot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * @author TQ
 * @Description
 * @create 2021-11-10 14:22
 */
public class ScannerTest {
    public static void main(String[] args) {

            String str1 = null;
            String str2 = null;

            Scanner scan = new Scanner(System.in);
            // 从键盘接收数据
            // nextLine方式接收字符串
            // 判断是否还有输入
            if (scan.hasNextLine()) {

                str1 = scan.nextLine();

            }


            Scanner scan2 = new Scanner(System.in);
            if (scan2.hasNextLine()) {

                str2 = scan2.nextLine();

            }
            if (!(str1 == null) && !(str2 == null) && str1.length() >= 0 && str1.length() <= 1000 && str2.length() >= 0 && str2.length() <= 1000) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < str1.length(); i++) {
                    String s = str1.substring(i, i + 1);
                    list.add(s);
                }
                int i = 0;
                for (Iterator<String> iterator = list.iterator(); iterator.hasNext(); ) {
                    String ss = iterator.next();
                    // System.out.println(ss);
                    if (str2.equals(ss)) {
                        i++;
                    }
                }
                System.out.println(i);

            }
        }



}

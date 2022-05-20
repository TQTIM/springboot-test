package com.tq.springboot.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindFile {
    public static void main(String[] args) {
        //过滤后缀名的对象
        //     FileFilter filterByFile=(FileFilter) new FilterByFile("");//比如后缀名".txt"
        //路径下的文件对象
        File dir = new File("D:\\001008004008042\\20201214094700\\fullPackage\\content\\公积金中心表");
        File dir1 = new File("D:\\001008004008042\\20201214094700\\fullPackage\\content\\公积金系统表");
        //存储文件的list对象
        List<File> list = new ArrayList<File>();
        List<File> list1 = new ArrayList<File>();
        //调用相应方法，添加文件到集合
        list = getFileList(dir, list);
        list1 = getFileList(dir1, list1);
        for (File file : list) {
      //      System.out.println("list为---" + file.getName());

           /* for (File file2 : list1) {
     //          System.out.println("list1为---" + file2.getName());

                if (file.getName().equals(file2.getName())) {
                    System.out.println(file.getName()+"对应------》"+file2.getName()+"路径---》"+file.getPath()+"222"+file2.getPath());
                    //1.获取此时dov.xml的hash
                    String hash1 = MD5Util.getFileMD5(file);
                    String hash2=MD5Util.getFileMD5(file2);
                    if (hash1.equals(hash2)) {
                        System.out.println("-hash相同--->");
                    }

                    //2.获取此时check.xml的hash
                    //3.比较两个hash
                }
            }*/

        }



      /*  //只存储文件名和文件路径的map对象
        Map<String, String> map0 = new HashMap<String, String>();
        Map<String, String> map1 = new HashMap<String, String>();
        System.out.println("A文件夹的所有文件：");
        for (File file : list) {
            System.out.println("" + file);
            map0.put(file.getName(), file.getPath());
        }
        System.out.println("B文件夹的所有文件：");
        for (File file : list1) {
            System.out.println("" + file);
            map1.put(file.getName(), file.getPath());
        }*/
       /* map0.keySet().removeAll(map1.keySet());//移除文件名及后缀相同的文件
        System.out.println("A文件夹有而B文件夹没有的文件：");
        for (Map.Entry<String, String> entry : map0.entrySet()) {
            System.out.println(entry.getKey() + "  -->  " + entry.getValue());
        }*/

    }

    private static List<File> getFileList(File dir, List<File> list) {
        //遍历路径下的所有文件
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                getFileList(file, list);//如果是目录需要递归
            } else {

                list.add(file);

            }

        }
        return list;
    }
}
package com.tq.springboot.test;

import com.alibaba.fastjson.JSON;
import com.tq.springboot.entity.Chirldren;
import com.tq.springboot.entity.ClassJson;
import com.tq.springboot.entity.Code;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CodeToJson {
    public static void main(String[] args) throws IOException {
        InputStreamReader ins = new InputStreamReader(new FileInputStream("D:\\temp\\lxt.txt"));
        BufferedReader br = new BufferedReader(ins);
        //存放bean对象
        List<ClassJson> tlist = new ArrayList<ClassJson>();

        //读取txt
        String line = null;
        List<String> list = new ArrayList<String>();
        while ((line = br.readLine()) != null) {
            list.add(line);
        }
        br.close();
        //txt的每一行相当于一条数据，split按,作分隔符进行拆分。\\s+是正则表达式。
        for (String str : list) {
            String[] arrStr = str.split(":");
            ClassJson classJson = new ClassJson();
            classJson.setCode(arrStr[0].substring(1, arrStr[0].length() - 1));
            classJson.setText(arrStr[1].replace(",", "").substring(1, arrStr[1].length() - 2));
            tlist.add(classJson);
        }
        List<Code> codeList = new ArrayList<>();
        for (ClassJson classJson : tlist) {
            if (classJson.getCode().substring(2, 6).equals("0000")) {
                Code code = new Code();
                code.setCode(classJson.getCode());
                code.setName(classJson.getText());
                codeList.add(code);
            }
        }
        List<Code> codeList1=new ArrayList<>();
        for(Code c:codeList){
            List<Chirldren> chirldrens=new ArrayList<>();
            String key = c.getCode().substring(0,2);
           //得到所有对应的市
           for(ClassJson classJson:tlist){
               String key1=classJson.getCode().substring(2,6);
               String key2=classJson.getCode().substring(4,6);
               if(classJson.getCode().substring(0,2).equals(key) && !key1.equals("0000") && key2.equals("00") ){
                   Chirldren chirldren=new Chirldren();
                   chirldren.setCode(classJson.getCode());
                   chirldren.setName(classJson.getText());
                   chirldrens.add(chirldren);
               }
           }
            c.setChirldrens(chirldrens);
            codeList1.add(c);
       }
        List<Code> finalCodeList=new ArrayList<>();
        for(Code cc:codeList1) {
            String key = cc.getCode().substring(0, 2);
            if (cc.getChirldren().size() == 0) {
                List<Chirldren> chirldrens1 = new ArrayList<>();
                for (ClassJson classJson : tlist) {
                    String key3 = classJson.getCode().substring(2, 6);
                    if (classJson.getCode().substring(0, 2).equals(key) && !key3.equals("0000")) {
                        Chirldren chirldren1 = new Chirldren();
                        chirldren1.setCode(classJson.getCode());
                        chirldren1.setName(classJson.getText());
                        chirldrens1.add(chirldren1);
                    }
                }
                cc.setChirldrens(chirldrens1);
            } else {
                List<Chirldren> newChirldrens = new ArrayList<>();
                for (Chirldren chirldren/*市*/: cc.getChirldren()) {

                    //区
                    List<Chirldren> chirldrens2 = new ArrayList<>();
                    String key5 = chirldren.getCode().substring(2, 4);
                    for (ClassJson classJson : tlist) {
                        String key6 = classJson.getCode().substring(2, 4);
                        String key7 = classJson.getCode().substring(4, 6);
                        if (key5.equals(key6) && classJson.getCode().substring(0, 2).equals(key) && !key7.equals("00")) {
                            //得到所有的区
                            Chirldren chirldren2 = new Chirldren();
                            chirldren2.setCode(classJson.getCode());
                            chirldren2.setName(classJson.getText());
                            chirldrens2.add(chirldren2);
                        }
                    }
                    chirldren.setChirldrenList(chirldrens2);
                    newChirldrens.add(chirldren);
                }
                cc.setChirldrens(newChirldrens);
            }
            finalCodeList.add(cc);
        }

        System.out.println(finalCodeList.size());
       //JSON.toJSONString()方法：将对象数组（JSON格式的字符串也可以）转换成JSON数据。
        String json = JSON.toJSONString(codeList1);
        System.out.println(json);
       //创建新文件
        File txtToJson = new File("D:\\temp\\lxt.json");
        txtToJson.createNewFile();
        BufferedWriter out = new BufferedWriter(new FileWriter(txtToJson));
        out.write(json);
        out.flush(); // 把缓存区内容压入文件
        out.close(); // 最后记得关闭文件
    }

}

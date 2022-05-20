package com.tq.springboot.test;

import com.tq.springboot.entity.ClassJson;
import com.tq.springboot.entity.Code;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: tq
 * @Date: 2022/1/12
 * @Description 文本区划转成分组后的json
 * @Version: 1.0
 */
public class CodeToJson2 {
    public static void main(String[] args) {
        List<ClassJson> tlist = null;
        List<String> list = null;
        try {
            InputStreamReader ins = new InputStreamReader(new FileInputStream("D:\\temp\\lxt.txt"));
            BufferedReader br = new BufferedReader(ins);
            //存放bean对象
            tlist = new ArrayList<ClassJson>();

            //读取txt
            String line = null;
            list = new ArrayList<String>();
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //txt的每一行相当于一条数据，split按,作分隔符进行拆分。\\s+是正则表达式。
        if(list!=null&&list.size()!=0) {
            for (String str : list) {
                String[] arrStr = str.split(":");
                ClassJson classJson = new ClassJson();
                classJson.setCode(arrStr[0].substring(1, arrStr[0].length() - 1));
                classJson.setText(arrStr[1].replace(",", "").substring(1, arrStr[1].length() - 2));
                tlist.add(classJson);
            }
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
    }
}

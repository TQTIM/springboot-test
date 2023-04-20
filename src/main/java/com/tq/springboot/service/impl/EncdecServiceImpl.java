package com.tq.springboot.service.impl;

import com.tq.springboot.service.EncdecService;
import com.tq.springboot.utils.AESUtil;
import com.tq.springboot.utils.Base64FileUtil;
import com.tq.springboot.utils.HttpClientUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Auther: tq
 * @Date: 2023/4/19
 * @Description 文件加密解密
 * @Version: 1.0
 */
@Service
public class EncdecServiceImpl implements EncdecService {
    @Override
    public String encrypt(String str) {

        String filePath ="C:\\Users\\TQ\\Desktop\\测试文件.xlsx";
        //推送地址
        String url = "http://localhost:8081/encdec/decrypt";
        String encryptStr = null;

        try {
            //文件转base64
            String baseFileStr = Base64FileUtil.getFileStr(filePath);
            //System.out.println(baseFileStr);

            //aes加密
             encryptStr = AESUtil.encrypt(baseFileStr);
            //System.out.println(encryptStr);

            //http调用
            String s = HttpClientUtil.doPost(url, encryptStr);
            System.out.println(s);


        }catch (Exception e){
            e.printStackTrace();
        }

        return encryptStr;

    }


    @Override
    public void decrypt(String encStr) {
        String targetPath ="C:\\Users\\TQ\\Desktop\\测试文件2.xlsx";

        try {
            //aes解密
            String decryptStr = AESUtil.decrypt(encStr);
            System.out.println(decryptStr);

            //base64转文件
            Base64FileUtil.generateFile(decryptStr,targetPath);

        }catch (Exception e){
            e.printStackTrace();
        }

    }


}

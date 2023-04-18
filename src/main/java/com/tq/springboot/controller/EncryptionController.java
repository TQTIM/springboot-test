package com.tq.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.tq.springboot.utils.AESUtil;
import com.tq.springboot.utils.Base64FileUtil;
import com.tq.springboot.utils.HttpClientUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: tq
 * @Date: 2023/4/18
 * @Description 文件加密解密
 * @Version: 1.0
 */
@RestController
public class EncryptionController {
    /**
     * 文件加密以及http调用
     * @param str
     * @throws Exception
     */
    @PostMapping("/encdec/encrypt")
    public void encrypt(@RequestBody String str) throws Exception {

        String filePath ="C:\\Users\\TQ\\Desktop\\唐武斌-简历.docx";
        //推送地址
        String url = "http://www.baidu.com.com/upload";

        //文件转base64
        String baseFileStr = Base64FileUtil.getFileStr(filePath);
        System.out.println(baseFileStr);

        //aes加密
        String encryptStr = AESUtil.encrypt(baseFileStr);
        System.out.println(encryptStr);

        //http调用
        String s = HttpClientUtil.doPost(url, encryptStr);
        System.out.println(s);


    }

    /**
     * 解密以及文件保存
     * @param encStr  加密文件字符串
     * @throws Exception
     */
    @PostMapping("/encdec/decrypt")
    public void decrypt(@RequestBody String encStr) throws Exception {

        String targetPath ="C:\\Users\\TQ\\Desktop\\唐武斌-简历2.docx";

        //aes解密
        String decryptStr = AESUtil.decrypt(encStr);
        System.out.println(decryptStr);

        //base64转文件
        Base64FileUtil.generateFile(decryptStr,targetPath);

    }


}

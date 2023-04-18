package com.tq.springboot;

import com.tq.springboot.utils.AESUtil;
import com.tq.springboot.utils.Base64FileUtil;
import com.tq.springboot.utils.MD5Utils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @Auther: tq
 * @Date: 2023/4/18
 * @Description
 * @Version: 1.0
 */
@SpringBootTest
public class Base64Test {
    @Test
    public void fileToBase64() throws IOException {
        // ---------------加密文件---------------
        String filePath ="C:\\Users\\TQ\\Desktop\\测试文件.xlsx";
        String baseFileStr = Base64FileUtil.getFileStr(filePath);
        System.out.println(baseFileStr);

        System.out.println("加密文件: "+baseFileStr);

    }

    @Test
    public void base64ToFile() throws Exception {
        //文件转base64
        String filePath ="C:\\Users\\TQ\\Desktop\\唐武斌-简历.docx";
        String baseFileStr = Base64FileUtil.getFileStr(filePath);
        System.out.println(baseFileStr);

        //aes加密
        String encryptStr = AESUtil.encrypt(baseFileStr);
        System.out.println(encryptStr);

        //aes解密
        String decryptStr = AESUtil.decrypt(encryptStr);
        System.out.println(decryptStr);

        String targetPath ="C:\\Users\\TQ\\Desktop\\唐武斌-简历2.docx";
        Base64FileUtil.generateFile(decryptStr,targetPath);
    }

    @Test
    //整体过程
    public void base64Test() throws Exception {
        String filePath = "path/to/file";
        String url = "http://example.com/upload";
        String key = "1234567890123456";
        String iv = "1234567890123456";

        // 读取文件并转换为base64编码
        File file = new File(filePath);
        byte[] fileBytes = new byte[(int) file.length()];
        FileInputStream fis = new FileInputStream(file);
        fis.read(fileBytes);
        fis.close();
        String base64String = Base64.getEncoder().encodeToString(fileBytes);

        // 使用aes加密
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(keyBytes);
        keyGenerator.init(128, secureRandom);
        Key secretKey = keyGenerator.generateKey();
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
        byte[] encryptedBytes = cipher.doFinal(base64String.getBytes(StandardCharsets.UTF_8));
        String encryptedString = Base64.getEncoder().encodeToString(encryptedBytes);

        // http调用第三方接口
       /* CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setEntity(new StringEntity("{\"data\":\"" + encryptedString + "\"}"));
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity responseEntity = httpResponse.getEntity();
        String responseString = new String(responseEntity.getContent().readAllBytes(), StandardCharsets.UTF_8);
        System.out.println(responseString);*/
    }
}

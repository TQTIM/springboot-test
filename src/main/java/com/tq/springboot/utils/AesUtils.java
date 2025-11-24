package com.tq.springboot.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * @author TQ
 * @Description aes加密解密
 */
public class AesUtils {
    // 密钥
    private static final String KEY = "1234567890123456";
    // 加密算法
    private static final String ALGORITHM = "AES/ECB/PKCS5Padding";

    public static String encrypt(String content) throws Exception {
        // 获取加密算法实例
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // 初始化加密模式
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(KEY.getBytes(), "AES"));
        // 加密
        byte[] encryptedBytes = cipher.doFinal(content.getBytes());
        // 返回加密后的字符串
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String content) throws Exception {
        // 获取加密算法实例
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // 初始化解密模式
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(KEY.getBytes(), "AES"));
        // 解密
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(content));
        // 返回解密后的字符串
        return new String(decryptedBytes);
    }
}
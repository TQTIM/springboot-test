package com.tq.springboot.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Auther: tq
 * @Date: 2023/4/18
 * @Description
 * @Version: 1.0
 */
public class MD5Utils {
    /**
     * TODO 加密文件
     * 1.传入的是"文件",不是字符串，所以信息摘要对象.进行摘要得到数组不能像上面获得:md5.digest(bytes),因为不是source.getBytes得到bytes
     * 2.其实还是通过mdt.digest();获取到字节数组,但是前期必须要有一个方法必须是md5.update(),即"信息摘要对象"要先更新
     * 3."信息摘要更新"里面有(byte[] input),说明是读取流获取到的数组,所以我们就用这个方法.
     * 4.所以最终的逻辑就是:
     * <p>
     * 1.获取文件的读取流
     * 2.不停的读取流中的"内容"放入字符串,放一部分就"更新"一部分.直到全部完毕
     * 3.然后调用md5.digest();就会得到有内容的字节数组,剩下的就和上边一样了.
     */
    public static String getFileMD5(File file) throws IOException {

        try {
            // 1.获取 MessageDigest 对象，String algorithm = "md5";可以清晰的看出是那种加密方式
            String algorithm = "md5";
            MessageDigest md5 = MessageDigest.getInstance(algorithm);
            // 2.加载文件
            FileInputStream files = new FileInputStream(file);
            byte[] bytes = new byte[1024 * 5];

            int len = -1;
            // 3.读取文件
            while ((len = files.read(bytes)) != -1) {
                // 4.一部分一部分更新
                md5.update(bytes, 0, len);
            }
            byte[] digest = md5.digest();
            // 5..创建 BigInteger 对象，signum为1的时候是正数
            int signum = 1;
            BigInteger bigInteger = new BigInteger(signum, digest);
            // 6..按照 16 进制将 bigInteger 的值转换为字符串
            int radix = 16;
            String encoded = bigInteger.toString(radix).toUpperCase();
            return encoded;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }


}

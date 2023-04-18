package com.tq.springboot.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * @Auther: tq
 * @Date: 2023/4/18
 * @Description
 * @Version: 1.0
 */
public class Base64FileUtil {
    private static Logger logger = LoggerFactory.getLogger(Base64FileUtil.class);
    /**
     * 文件转化成base64字符串
     * 将文件转化为字节数组字符串，并对其进行Base64编码处理
     * @param filePath 文件路径
     */
    public static String getFileStr(String filePath) {

        InputStream in = null;
        byte[] data = null;
        // 读取文件字节数组
        try {
            in = new FileInputStream(filePath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            logger.warn("文件转化成base64字符串出现异常:{}",e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                logger.warn("文件转化成base64字符串出现异常:{}",e);
            }
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回 Base64 编码过的字节数组字符串
        return encoder.encode(data);
    }

    /**
     * base64字符串转化成文件，可以是JPEG、PNG、TXT和excel等等
     *
     * @param base64FileStr base64字符串
     * @param filePath 生成文件路径
     * @return
     * @throws Exception
     */
    public static boolean generateFile(String base64FileStr, String filePath) throws Exception {
        // 数据为空
        if (base64FileStr == null) {
            System.out.println("传入的字符串为空！ ");
            return false;
        }
        BASE64Decoder decoder = new BASE64Decoder();

        // Base64解码,对字节数组字符串进行Base64解码并生成文件
        byte[] byt = decoder.decodeBuffer(base64FileStr);
        for (int i = 0, len = byt.length; i < len; ++i) {
            // 调整异常数据
            if (byt[i] < 0) {
                byt[i] += 256;
            }
        }
        OutputStream out = null;
        InputStream input = new ByteArrayInputStream(byt);
        try {
            // 生成指定格式的文件
            out = new FileOutputStream(filePath);
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = input.read(buff)) != -1) {
                out.write(buff, 0, len);
            }
        } catch (IOException e) {
            logger.warn("base64字符串转化成文件出现异常:{}",e);
        } finally {
            out.flush();
            out.close();
        }
        return true;
    }

}

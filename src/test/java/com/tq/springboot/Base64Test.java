package com.tq.springboot;

import com.tq.springboot.utils.Base64FileUtil;
import com.tq.springboot.utils.MD5Utils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;

/**
 * @Auther: tq
 * @Date: 2023/4/18
 * @Description
 * @Version: 1.0
 */
@SpringBootTest
public class Base64Test {
    @Test
    public void base64Test() throws IOException {
        // ---------------加密文件---------------
        String filePath ="C:\\Users\\TQ\\Desktop\\测试文件.xlsx";
        String baseFileStr = Base64FileUtil.getFileStr(filePath);
        System.out.println(baseFileStr);

        System.out.println("加密文件: "+baseFileStr);

    }

    @Test
    public void baseToFile() throws Exception {
        //base64加密
        String filePath ="C:\\Users\\TQ\\Desktop\\测试文件.xlsx";
        String baseFileStr = Base64FileUtil.getFileStr(filePath);
        System.out.println(baseFileStr);

        String targetPath ="C:\\Users\\TQ\\Desktop\\测试文件2.xlsx";
        Base64FileUtil.generateFile(baseFileStr,targetPath);
    }
}

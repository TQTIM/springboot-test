package com.tq.springboot;

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
public class MD5Test {
    @Test
    public void md5Test() throws IOException {
        // ---------------加密文件---------------
        File file = new File("C:\\Users\\TQ\\Desktop\\测试文件.xlsx");
        String md2 = MD5Utils.getFileMD5(file);

        System.out.println("加密文件: "+md2);
    }
}

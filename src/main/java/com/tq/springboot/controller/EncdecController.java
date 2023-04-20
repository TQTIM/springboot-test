package com.tq.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.tq.springboot.service.EncdecService;
import com.tq.springboot.utils.AESUtil;
import com.tq.springboot.utils.Base64FileUtil;
import com.tq.springboot.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
public class EncdecController {
    @Autowired
    private EncdecService encdecService;
    /**
     * 文件加密以及http调用
     * @param str
     * @throws Exception
     */
    @PostMapping("/encdec/encrypt")
    public String encrypt(@RequestBody String str) {

        return encdecService.encrypt(str);

    }

    /**
     * 解密以及文件保存
     * @param encStr  加密文件字符串
     * @throws Exception
     */
    @PostMapping("/encdec/decrypt")
    public void decrypt(@RequestBody String encStr) {

        encdecService.decrypt(encStr);

    }


}

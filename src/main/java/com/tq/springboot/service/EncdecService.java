package com.tq.springboot.service;

import java.io.IOException;

/**
 * @Auther: tq
 * @Date: 2023/4/19
 * @Description
 * @Version: 1.0
 */
public interface EncdecService {
    String encrypt(String str);
    void decrypt(String encStr);
}

package com.tq.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MatcherTest {
    @Test
    public void test(){
        String pwd = "Tang123&tang123&";
        String pattern ="^(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,16}";

        System.out.println("输出："+pwd.matches(pattern));

    }
}

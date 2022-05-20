package com.tq.springboot;

import com.tq.springboot.entity.CityEnums;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Auther: tq
 * @Date: 2022/4/19
 * @Description
 * @Version: 1.0
 */
@SpringBootTest
public class CityEnumsTest {
    @Test
    public void enumsTest(){
        String orgId = CityEnums.getOrgId("529");
        System.out.println(orgId);

    }
}

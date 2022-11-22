package com.tq.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @Auther: tq
 * @Date: 2022/8/22
 * @Description
 * @Version: 1.0
 */
@SpringBootTest
public class CalenderTest {

    @Test
    public void belongCalendar() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date nowTime = sdf.parse("2021-10");
        Date beginTime = sdf.parse("2021-10-03");
        Date endTime = sdf.parse("2021-11-30");

        String b = belongCalendar(nowTime, beginTime, endTime);
        System.out.println("返回值："+b);


    }
    /**
     * 判断一个时间是否在一个时间段内
     */
    private String belongCalendar(Date dateTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(dateTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if(begin.after(date) || begin.equals(date)){//设定时间<=开始和结束时间
            return "0";
        }else {
            if(begin.before(date) && (end.after(date) || end.equals(date) )){//设定时间>开始时间 <=结束时间
                return "1";
            }else {  //设定时间>开始和结束时间
                return "2";
            }
        }

    }

    @Test
    public void timeTest(){
        String created = "2022092216";
        String newTime = created.substring(0,4)+"-"+created.substring(4,6)+"-"+created.substring(6,8)+" "+created.substring(8,10)+":00:00";
        System.out.println(newTime);

    }
}

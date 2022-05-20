package com.tq.springboot.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Auther: tq
 * @Date: 2022/4/12
 * @Description
 * @Version: 1.0
 */
public class CalendarTest {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date outStartTime = format.parse("2022-01-05");
        Date outEndTime = format.parse("2022-01-12");
        Calendar startTime = Calendar.getInstance();
        Calendar endTime = Calendar.getInstance();
        startTime.setTime(outStartTime);
        endTime.setTime(outEndTime);
        long start = startTime.getTimeInMillis();
        long end = endTime.getTimeInMillis();
        long between_days = (end - start) / (1000 * 3600 * 24);

        System.out.println((int)between_days+1);
    }

}

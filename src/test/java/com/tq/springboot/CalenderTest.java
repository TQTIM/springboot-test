package com.tq.springboot;

import com.tq.springboot.utils.DateUtils;
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

    @Test
    public void isBetweenMonthTest(){
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2022, 7,1);
        Date date = calendar1.getTime();
        DateUtils.isBetweenMonth(date,-3);

    }

    // 获取今年第一天
    @Test
    public void firstDayOfYear(){
        Calendar currCal=Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR,currCal.get(Calendar.YEAR));
        Date time = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String firstday =  format.format(time);
        System.out.println(firstday);

        String nowDay = format.format(new Date());
        System.out.println(nowDay);
        System.out.println(firstday.equals(nowDay));
    }

    //日期之前之后
    @Test
    public void dateTest() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date birthday = sdf.parse("19940304");
        if(birthday.after(new Date()) ){
            System.out.println("之后");
        }else {
            System.out.println("之前");
        }

    }

    //2月平年闰年
    @Test
    public void calenderFeb() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date birthday = sdf.parse("20080229");

        Calendar birthdayCdr = DateUtils.toCalendar(birthday);
        if (birthdayCdr.get(Calendar.MONTH) == 1 && birthdayCdr.get(Calendar.DAY_OF_MONTH) == 29) {
            Calendar nowCdr = Calendar.getInstance();
            int year = nowCdr.get(Calendar.YEAR);

            nowCdr.clear();
            nowCdr.set(year, 1, 28);
            nowCdr.add(Calendar.DAY_OF_MONTH, 1);
            //判断，如果月份进一，说明有28天，如果没进说明有29天，闰年
            if (nowCdr.get(Calendar.MONTH) == 2) {
                //平年，不生成2月29号生日
                System.out.println("平年");
            }

        }


//
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(birthday);
        Calendar calendar2 = Calendar.getInstance();
        if(calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)){
            System.out.println("月份一致");
        }
    }

}

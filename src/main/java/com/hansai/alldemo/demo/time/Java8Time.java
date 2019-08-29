package com.hansai.alldemo.demo.time;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * <p>Description: </p>
 * <p> </p>
 *
 * @author: hansai
 * @date: 2019/6/20
 * @time: 15:13
 */
public class Java8Time {

    /**
     * 测试LocalTime
     */
    public void localTimeTest() {
        //获取当前时间
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println("当前时间"+dateTime);
        //格式化日期
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattime=dateTime.format(formatter);
        System.out.println("格式化时间"+formattime);
        //获取年月日
        System.out.print(dateTime.getYear()+"年 "+dateTime.getMonthValue()+"月 "+dateTime.getDayOfMonth() +"日");
        //获取时分秒
        System.out.print(dateTime.getHour()+"点"+dateTime.getMinute()+"分"+dateTime.getSecond()+"秒");
        //获取纳秒
        System.out.println(dateTime.getNano());
        //生成新的时间
        LocalDateTime dateTime2 = dateTime.withDayOfMonth(10).withYear(2111);
        System.out.println("新的时间"+dateTime2.format(formatter));

        //获取指定时区的当前时间
        LocalDateTime shanghaiTime = LocalDateTime.now(ZoneId.of("CTT",ZoneId.SHORT_IDS));
        System.out.println("当前时间设置为上海时区："+shanghaiTime.format(formatter));
        //设置时区
        System.out.println("设置时区："+dateTime.atZone(ZoneId.of("CTT",ZoneId.SHORT_IDS)));

    }

    public void localDateTest() {

        /*******************************************/
        //获取当前日期
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(localDate.format(dateformatter));
        //
    }

}

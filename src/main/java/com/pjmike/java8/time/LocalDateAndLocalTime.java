package com.pjmike.java8.time;

import java.time.*;
import java.time.temporal.ChronoUnit;

/**
 * @description:
 * @author: 13572
 * @create: 2019/06/28 14:17
 */
public class LocalDateAndLocalTime {
    public static void main(String[] args) {
        //方式一:  通过静态工厂方法创建
        LocalDate localDate = LocalDate.of(2019,6,28);
        LocalTime localTime = LocalTime.of(14, 19, 30);
        System.out.printf("localDate: %s , localTime: %s \n", localDate,localTime);

        //方式二:  从系统时钟中获取当前时间
        LocalTime now = LocalTime.now();
        System.out.printf("now ：%s \n", now);

        //方式三:  使用静态方法parse解析字符串
        LocalDate date = LocalDate.parse("2019-06-28");
        LocalTime time = LocalTime.parse("14:19:30");
        System.out.printf("date: %s ; time : %s \n", date, time);

        //方式四: 使用复合类LocalDateTime，它是LocalTime和LocalDate的复合体
        // 表示日期和时间，不带有时区信息
        LocalDateTime localDateTime = LocalDateTime.of(2019, Month.JUNE, 28, 14, 33);
        System.out.printf("localDateTime: %s \n", localDateTime);

        //比较两个时间的时间差
        LocalTime t1 = LocalTime.of(15, 33, 33);
        LocalTime t2 = LocalTime.of(15, 34, 33);
        Duration d1 = Duration.between(t1, t2);
        System.out.println("d1: " + d1.getSeconds());

        LocalDate date1 = LocalDate.of(2019, 6, 28);
        LocalDate date2 = LocalDate.of(2019, 7, 28);
        Period period = Period.between(date1, date2);
        System.out.println("相差月数: " + period.getMonths());
        //相差天数，period比较的是相对天数，比如6.28与7月28相对天数为0
        //所以使用until方法,并指明精度单位是days
        System.out.println(date1.until(date2, ChronoUnit.DAYS));

        //使用时区，获取当前美国纽约的时间
        System.out.println(LocalDateTime.now(ZoneId.of("America/New_York")));

    }
}

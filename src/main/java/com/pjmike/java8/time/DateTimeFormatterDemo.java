package com.pjmike.java8.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @description:
 * @author: 13572
 * @create: 2019/06/28 09:33
 */
public class DateTimeFormatterDemo {
    public static void main(String[] args) {
        //通过DateTimeFormatter格式化日期，所有DateTimeFormatter实例都是线程安全的
        LocalDate date = LocalDate.of(2019, 6, 28);
        String s1 = date.format(DateTimeFormatter.BASIC_ISO_DATE); //20190628
        String s2 = date.format(DateTimeFormatter.ISO_DATE); //2019-06-28
        System.out.printf("s1: %s , s2: %s", s1, s2);
    }
}

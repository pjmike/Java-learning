package com.pjmike.java8;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/04/08 22:38
 */
public class ConverterTest {

    public static void main(String[] args) {
         int num = 1;
        Converter<String, Integer> converter = (from ->
                Integer.valueOf(from + num));
        System.out.println(converter.convert("123"));
    }
}

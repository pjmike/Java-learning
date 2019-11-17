package com.pjmike.StringPool;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/04/03 22:06
 */
public class StringTest {
    public static void main(String[] args) {
        String s11 = new String("11");
        s11.intern();
        String s12 = "11";
        System.out.println(s11 == s12);
    }
}

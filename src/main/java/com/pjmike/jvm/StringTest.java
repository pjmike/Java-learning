package com.pjmike.jvm;

import java.util.Stack;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/01/26 16:32
 */
public class StringTest {
    public static void main(String[] args) {
        String s11 = new String("13555");
        s11.intern();
        String s12 = "13555";
        System.out.println(s11 == s12);
    }
}

package com.pjmike.effective_java;

/**
 * @description: 要想让断言有效，需使用-ea4
 * @author: pjmike
 * @create: 2019/11/17
 */
public class Test {
    public static void main(String[] args) {
        Test.sort(null);
    }
    private static void sort(int[] a) {
        assert a != null;
        System.out.println("hello world");
    }
}

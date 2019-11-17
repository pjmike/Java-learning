package com.pjmike;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/02/18 00:09
 */
public class IntegerTest {
    public static void main(String[] args) {
        Integer a = 10;
        int b = a;
        int c = 20;
        Integer l1 = 127, l2 = 127, l3 = 128, l4 = 128;
        System.out.printf("l1==l2结果是 %b,而l1.equals(l2)的结果是：%b\n",l1==l2,l1.equals(l2));
        System.out.printf("l3==l4结果是 %b,而l3.equals(l4)的结果是：%b",l3==l4,l3.equals(l4));
    }
}

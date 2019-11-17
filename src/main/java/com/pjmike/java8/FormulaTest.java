package com.pjmike.java8;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/04/08 21:47
 */
public class FormulaTest {
    public static void main(String[] args) {
        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };
        System.out.println(formula.calculate(100));
        System.out.println(formula.sqrt(16));
    }
}

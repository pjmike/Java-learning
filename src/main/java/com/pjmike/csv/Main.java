package com.pjmike.csv;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/03/01 22:39
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("A");
        new Main();
        new Main();
    }
    {
        System.out.println("C");
    }
    public Main() {
        System.out.println("B");
    }



    static {
        System.out.println("D");
    }
}

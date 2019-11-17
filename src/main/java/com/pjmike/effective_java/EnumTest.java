package com.pjmike.effective_java;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/11/14
 */
public enum EnumTest {
    LOGIN(1),
    SEND(2),
    QUIT(3);
    private final int number;

    EnumTest(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}

package com.pjmike.jvm.bytecode.asm;

/**
 * @description:
 * @author: 13572
 * @create: 2019/05/01 19:10
 */
public class ASMTest {
    public static void main(String[] args) {
        System.out.println("main process");
        new ASMTest().process();
    }

    public void process() {
        step1();
        step2();
    }

    public void step1() {
        System.out.println("in step1");
    }

    public void step2() {
        System.out.println("in step2");
    }
}

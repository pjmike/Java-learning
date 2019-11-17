package com.pjmike.jvm;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/02/26 21:01
 */
public class JavaVMStackOF {
    private int count = 1;

    public void stackLeak() {
        count++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackOF java = new JavaVMStackOF();
        try {
            java.stackLeak();
        } catch (Throwable throwable) {
            System.out.println("栈深度： " + java.count);
            throw throwable;
        }
    }
}

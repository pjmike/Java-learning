package com.pjmike.jmx;

/**
 * @description:
 * @author: 13572
 * @create: 2019/05/26 16:53
 */
public class Hello implements HelloMBean{
    private final String name = "pjmike";
    private final int age = 21;
    @Override
    public void sayHello() {
        System.out.println("hello world");
    }

    @Override
    public int add(int x, int y) {
        return x + y;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getAge() {
        return this.age;
    }
}

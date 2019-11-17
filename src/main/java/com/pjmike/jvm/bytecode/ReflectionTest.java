package com.pjmike.jvm.bytecode;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @description: 反射测试
 * @author: 13572
 * @create: 2019/05/01 16:28
 */
public class ReflectionTest {
    public static void print(int i) {
        new Exception("test#"+i).printStackTrace();
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InterruptedException, IOException {
        Class<?> clazz = Class.forName("com.pjmike.jvm.bytecode.ReflectionTest");
        Method method = clazz.getMethod("print",int.class);
        for (int i = 0; i <20 ; i++) {
            method.invoke(null,i);
        }
        System.in.read();
    }
}

package com.pjmike.jvm.heap;

/**
 * 打印堆内存
 * input：java -Xmx33m com.pjmike.jvm.heap.JvmXmxArgs a b
 * output: args a args b -Xmx:33M
 * @author pjmike
 * @create 2018-09-03 14:45
 */
public class JvmXmxArgs {
    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println("args: "+arg);
        }
        //堆内存,ps: -Xms和-Xmx这类参数的默认单位为 Byte,所以要想获得 "M"，要进行运算转换单位
        System.out.println("-Xmx:" + Runtime.getRuntime().maxMemory() / 1024 / 1024 + "M");
    }


}

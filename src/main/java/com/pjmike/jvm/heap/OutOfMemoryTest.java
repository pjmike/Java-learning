package com.pjmike.jvm.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:  -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=E:\
 * @author: pjmike
 * @create: 2019/02/26 20:42
 */
public class OutOfMemoryTest {
    static class OOMObject {

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}

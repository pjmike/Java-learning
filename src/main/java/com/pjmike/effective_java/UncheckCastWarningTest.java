package com.pjmike.effective_java;

import com.alibaba.fastjson.JSON;

import java.lang.annotation.Annotation;
import java.util.ArrayList;


/**
 * @author: pjmike
 * @create: 2019/11/11
 */
public class UncheckCastWarningTest {
    private int size;
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("d");
        list.add("a");
        list.add("c");
        list.add("e");
        String[] hello = new String[2];
        String[] strings = list.toArray(hello);
        System.out.println(JSON.toJSONString(hello));
        System.out.println(JSON.toJSONString(strings));
    }
}

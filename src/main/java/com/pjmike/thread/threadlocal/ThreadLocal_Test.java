package com.pjmike.thread.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/01/22 20:15
 */
public class ThreadLocal_Test {
    private static ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    public String formatDate(Date date) {
        return formatter.get().format(date);
    }
}

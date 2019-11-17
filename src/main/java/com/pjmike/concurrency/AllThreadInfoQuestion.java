package com.pjmike.concurrency;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @description: JMX //TODO 获取当前JVM线程的线程状态
 * @author: pjmike
 * @create: 2019/04/07 21:35
 */
public class AllThreadInfoQuestion {
    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long[] threadIds = threadMXBean.getAllThreadIds();
        for (long threadId : threadIds) {
            ThreadInfo info = threadMXBean.getThreadInfo(threadId);
            System.out.println(info.toString());
        }
    }
}

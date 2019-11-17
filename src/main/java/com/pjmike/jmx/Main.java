package com.pjmike.jmx;

import javax.management.*;
import java.lang.management.ManagementFactory;

/**
 * @description:
 * @author: 13572
 * @create: 2019/05/26 16:56
 */
public class Main {
    public static void main(String[] args) throws Exception{
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("com.pjmike.jmx:type=Hello");

        Hello hello = new Hello();
        mBeanServer.registerMBean(hello, name);

        Thread.sleep(Long.MAX_VALUE);
    }
}

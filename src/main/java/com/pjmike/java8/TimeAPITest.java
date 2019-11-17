package com.pjmike.java8;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalTime;
import java.util.Date;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/04/09 15:09
 */
public class TimeAPITest {
    public static void main(String[] args) {
        Clock clock = Clock.systemDefaultZone();
        long millis = clock.millis();

        Instant instant = clock.instant();

        Date legacyDate = Date.from(instant);
        System.out.println(legacyDate);

        LocalTime late = LocalTime.of(23, 34, 34);
        System.out.println(late);
    }
}

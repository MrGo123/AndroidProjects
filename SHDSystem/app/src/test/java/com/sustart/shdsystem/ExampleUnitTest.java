package com.sustart.shdsystem;

import org.junit.Test;

import java.util.Date;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        System.out.println("时间是：");
        System.out.println(new Date());
        System.out.println("时间戳：");
        long timestamp = System.currentTimeMillis();
        System.out.println(timestamp);
        System.out.println("时间戳转日期：" + new Date(timestamp));

    }
}
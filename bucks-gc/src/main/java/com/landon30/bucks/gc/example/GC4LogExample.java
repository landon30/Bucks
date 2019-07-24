/**
 * Copyright (c) 2019 landon30
 */

package com.landon30.bucks.gc.example;

import java.util.concurrent.TimeUnit;

/**
 * GC样例程序，测试gc日志相关参数
 *
 * @date 2019-07-23
 * @author landon30
 */
public class GC4LogExample {

    public static void main(String[] args) throws Exception {
        byte[] b1 = new byte[65536];

        while (true) {
            TimeUnit.MILLISECONDS.sleep(10);
            byte[] bytes = new byte[1024];
        }
    }
}

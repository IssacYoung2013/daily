package com.issac.interview.jvm.model;

import java.util.Random;

/**
 * @author: ywy
 * @date: 2019-12-31
 * @desc:
 */
public class PermGenTest {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            // 添加到字符串常量池
            getRandomString(10000000).intern();
        }
        System.out.println("Mission Complete!");
    }

    private static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int j = 0; j < length; j++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }

        return sb.toString();
    }
}

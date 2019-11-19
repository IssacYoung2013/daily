package com.dialog.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author: ywy
 * @date: 2019-09-28
 * @desc:
 */
public class InputUtil {
    public static final BufferedReader KEYBOARD_INPUT = new BufferedReader(new InputStreamReader(System.in));

    public static String getString(String prompt) {
        boolean flag = true;
        String str = null;
        while (flag) {
            System.out.println(prompt);
            try {
                String inputData = KEYBOARD_INPUT.readLine().trim();
                if(inputData == null || "".equals(inputData)) {
                    System.out.println("input cannot be null or empty");
                }
                str = inputData;
                flag = false;
            } catch (IOException e) {
                System.out.println("input cannot be null or empty");
            }
        }
        return str;

    }
}

package com.issac.interview.thread;

/**
 * @author: ywy
 * @date: 2020-01-03
 * @desc:
 */
public class CoarseSync {
    public static String copyString(String target) {
        int i = 0;
        StringBuffer sb = new StringBuffer();
        while (i < 100) {
            sb.append(target);
            i++;
        }
        return sb.toString();
    }

    public static void main(String[] args) {

    }
}

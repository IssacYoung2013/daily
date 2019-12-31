package com.issac.interview.bytecode;

/**
 * @author: ywy
 * @date: 2019-12-29
 * @desc:
 */
public class ByteCodeSample {
    public static void main(String[] args) {
//        Class.forName()
        int i = 1,j =5;
        i++;
        ++j;
        System.out.println(i);
        System.out.println(j);
    }
}

package com.issac.jvm.charpter2;

/**
 * @author: ywy
 * @date: 2019-11-20
 * @desc:
 */
public class RuntimeConstantPoolOOM2 {
    public static void main(String[] args) {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);

        // java 这个字符串常量已经有了
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }
}

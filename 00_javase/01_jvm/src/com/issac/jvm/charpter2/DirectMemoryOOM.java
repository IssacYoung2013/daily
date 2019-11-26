package com.issac.jvm.charpter2;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author: ywy
 * @date: 2019-11-20
 * @desc: -Xmx20M -XX:MaxDirectMemorySize=10M
 */
public class DirectMemoryOOM {
    public static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe un = (Unsafe) unsafeField.get(null);
        while (true) {
            un.allocateMemory(_1MB);
        }
    }
}

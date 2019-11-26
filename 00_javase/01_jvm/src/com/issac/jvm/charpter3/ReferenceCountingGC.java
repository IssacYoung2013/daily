package com.issac.jvm.charpter3;

/**
 * @author: ywy
 * @date: 2019-11-21
 * @desc:
 */
public class ReferenceCountingGC {

    public Object instance = null;

    public static final int _1MB = 1024 * 1024;

    /**
     * 这个成员属性的唯一意义就是占点内存，以便能在GC日志中看清楚是否被回收
     */
    private byte[] bigSize = new byte[2 * _1MB];

    public static void testGC() {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();

        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;

        // 假设在这里进行GC，objA和objB是否能被回收
        System.gc();
    }

    public static void main(String[] args) throws Exception {
        testGC();
        Thread.sleep(300000);
    }
}

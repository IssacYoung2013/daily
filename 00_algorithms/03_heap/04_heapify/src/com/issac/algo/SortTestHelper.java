package com.issac.algo;

import java.lang.reflect.Method;

/**
 * @author: ywy
 * @date: 2019-11-19
 * @desc:
 */
public class SortTestHelper {
    private SortTestHelper() {
    }

    /**
     * 生成随机数组
     *
     * @param n
     * @param rangeL
     * @param rangeR
     * @return
     */
    public static Integer[] generateRandomArray(int n, int rangeL, int rangeR) {
        assert rangeL <= rangeR;
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) ((Math.random() * (rangeR - rangeL + 1))+rangeL);
        }
        return arr;
    }

    public static Integer[] generateNearlyOrderArray(int n, int swapTimes) {
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }

        for (int i = 0; i < swapTimes; i++) {
            int a = (int) (Math.random() * n);
            int b = (int) (Math.random() * n);
            int t = arr[b];
            arr[b] = arr[a];
            arr[a] = t;
        }
        return arr;
    }

    /**
     * 打印数组
     *
     * @param arr
     */
    public static void printArray(Object arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            System.out.print(' ');
        }
        System.out.println();
    }

    public static boolean isSorted(Comparable[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (arr[i].compareTo(arr[j]) > 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void testSort(String sortClassName, Comparable[] arr) {
        try {
            Class<?> sortClass = Class.forName(sortClassName);
            Method sortMethod = sortClass.getDeclaredMethod("sort", new Class[]{Comparable[].class});
            sortMethod.setAccessible(true);
            Object[] params = new Object[]{arr};
            long beginTime = System.currentTimeMillis();
            sortMethod.invoke(null, params);
            long endTime = System.currentTimeMillis();
            if(!isSorted(arr)) {
                throw new IllegalStateException(sortClassName + " failed");
            }
            System.out.println(sortClass.getSimpleName() + " cost " + (endTime - beginTime) + "ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

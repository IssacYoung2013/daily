package com.issac.algo;

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
     * @param n
     * @param rangeL
     * @param rangeR
     * @return
     */
    public static Integer[] generateRandomArray(int n, int rangeL, int rangeR) {
        assert rangeL <= rangeR;
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * (rangeR - rangeL + 1));
        }
        return arr;
    }

    /**
     * 打印数组
     * @param arr
     */
    public void printArray(Object arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            System.out.print(' ');
        }
        System.out.println();
    }
}

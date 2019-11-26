package com.issac.algo;

/**
 * @author: ywy
 * @date: 2019-11-19
 * @desc: Shell排序 分组插入排序，当k=1时结束循环
 */
public class ShellSort {
    private ShellSort() {
    }

    public static void sort(Comparable[] arr) {
        int n = arr.length;
        int h = 1;
        // 分组
        while (h < n / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                Comparable e = arr[i];
                int j = i;
                for (; j >= h && e.compareTo(arr[j - h]) < 0; j -= h) {
                    arr[j] = arr[j - h];
                }
                if (j < i) {
                    arr[j] = e;
                }
            }
            h /= 3;
        }
    }

    public static void main(String[] args) {
        int N = 10000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 10000);
        SortTestHelper.testSort("com.issac.algo.ShellSort", arr);
    }
}

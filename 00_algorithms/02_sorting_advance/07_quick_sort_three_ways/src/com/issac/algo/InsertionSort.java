package com.issac.algo;

/**
 * @author: ywy
 * @date: 2019-11-19
 * @desc: 插入排序 O(n^2) 抓牌
 */
public class InsertionSort {
    private InsertionSort() {
    }

    public static void sort(Comparable[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            Comparable e = arr[i];
            int j = i;
            for (; j > 0 && e.compareTo(arr[j - 1]) < 0; j--) {
                arr[j] = arr[j - 1];
            }
            if (j < i) {
                arr[j] = e;
            }
        }
    }

    public static void main(String[] args) {
        int N = 10000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 10000);
        SortTestHelper.testSort("com.issac.algo.InsertionSort", arr);
    }
}

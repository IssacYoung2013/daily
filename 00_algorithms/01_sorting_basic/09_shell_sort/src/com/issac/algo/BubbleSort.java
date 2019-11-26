package com.issac.algo;

/**
 * @author: ywy
 * @date: 2019-11-19
 * @desc: 冒泡排序
 */
public class BubbleSort {
    private BubbleSort() {
    }

    public static void sort(Comparable[] arr) {
        int n = arr.length;
        int index = n;
        while (index > 0) {
            for (int i = 1; i < index; i++) {
                if (arr[i - 1].compareTo(arr[i]) > 0) {
                    swap(arr, i - 1, i);
                }
            }
            index--;
        }
    }

    static void swap(Object[] arr, int a, int b) {
        Object temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args) {
        int N = 10;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 10000);
        SortTestHelper.testSort("com.issac.algo.BubbleSort", arr);
    }
}

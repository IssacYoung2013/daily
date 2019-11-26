package com.issac.algo;

import java.util.Arrays;

/**
 * @author: ywy
 * @date: 2019-11-19
 * @desc:
 */
public class SelectionSort2 {
    private SelectionSort2() {
    }

    /**
     * 每一轮中同时找出最小值和最大值
     * 排队前后高矮，中间排
     *
     * @param arr
     */
    public static void sort(Comparable[] arr) {
        int n = arr.length;
        int left = 0;
        int right = n - 1;
        while (left < right) {
            int minIndex = left;
            int maxIndex = right;
            // 保证 arr[minIndex]<arr[maxIndex]
            if (arr[minIndex].compareTo(arr[maxIndex]) > 0) {
                swap(arr, minIndex, maxIndex);
            }
            // 找出剩余元素最小值，和最大值
            for (int i = left + 1; i <= right; i++) {
                if (arr[i].compareTo(arr[minIndex]) < 0) {
                    minIndex = i;
                }
                if (arr[i].compareTo(arr[maxIndex]) > 0) {
                    maxIndex = i;
                }
            }

            if (minIndex > left) {
                swap(arr, minIndex, left);
            }

            if (maxIndex < right) {
                swap(arr, maxIndex, right);
            }
            left++;
            right--;
        }
    }

    static void swap(Object[] arr, int a, int b) {
        Object temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args) {
        int N = 10000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 10000);
        Integer[] arr1 = Arrays.copyOf(arr, N);
        SortTestHelper.testSort("com.issac.algo.SelectionSort", arr);
        SortTestHelper.testSort("com.issac.algo.SelectionSort2", arr1);
    }
}

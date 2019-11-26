package com.issac.algo;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author: ywy
 * @date: 2019-11-19
 * @desc: 选择排序 O(n ^ 2)，队伍排队
 */
public class SelectionSort {
    private SelectionSort() {
    }

    public static void sort(Integer[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            if (minIndex > i) {
                swap(arr, i, minIndex);
            }
        }
    }

    static void swap(Integer[] arr, int a, int b) {
        Integer temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args) {
        int N = 10;
        Integer[] arr = {4, 5, 2, 1, 2, 4, 9};
        SelectionSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}

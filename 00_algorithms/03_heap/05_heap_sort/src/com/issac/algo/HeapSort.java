package com.issac.algo;

/**
 * @author: ywy
 * @date: 2019-11-19
 * @desc: 堆排序
 */
public class HeapSort {
    private HeapSort() {
    }

    public static void sort(Comparable[] arr) {
        int n = arr.length;
        for (int i = (n - 2) / 2; i >= 0; i--) {
            shiftDown(arr, i, n);
        }
        for (int i = n - 1; i >= 0; i--) {
            swap(arr, 0, i);
            shiftDown(arr, 0, i);
        }
    }

    private static void shiftDown(Comparable[] arr, int k, int n) {
        while (2 * k + 1 < n) {
            int j = 2 * k + 1;
            if (j + 1 < n && arr[j + 1].compareTo(arr[j]) > 0) {
                j++;
            }
            if (arr[k].compareTo(arr[j]) >= 0) {
                break;
            }
            swap(arr, k, j);
            k = j;
        }
    }

    private static void swap(Comparable[] arr, int a, int b) {
        Comparable temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args) {
        int N = 10000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 10000);
        SortTestHelper.testSort("com.issac.algo.HeapSort", arr);
    }
}

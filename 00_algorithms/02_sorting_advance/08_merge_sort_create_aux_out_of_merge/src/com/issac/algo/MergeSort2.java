package com.issac.algo;

import java.util.Arrays;

/**
 * @author: ywy
 * @date: 2019-11-19
 * @desc: 归并排序 O(nlogn)
 */
public class MergeSort2 {
    private MergeSort2() {
    }

    public static void sort(Comparable[] arr) {
        int n = arr.length;
        Comparable[] aux = new Comparable[n];
        mergeSort(arr, aux, 0, n - 1);
    }

    static void mergeSort(Comparable[] arr, Comparable[] aux, int l, int r) {
        if (r - l < 16) {
            insertionSort(arr, l, r);
            return;
        }
//        if (l >= r) {
//            return;
//        }
        // 避免溢出
        int m = (r - l) / 2 + l;
        mergeSort(arr, aux, l, m);
        mergeSort(arr, aux, m + 1, r);
        if (arr[m].compareTo(arr[m + 1]) > 0) {
            merge(arr, aux, l, m, r);
        }
    }

    /**
     * (l,m)(m+1,r)归并排序
     *
     * @param arr
     * @param l
     * @param m
     * @param r
     */
    private static void merge(Comparable[] arr, Comparable[] aux, int l, int m, int r) {
        System.arraycopy(arr, l, aux, l, r - l + 1);
//        Comparable[] aux = Arrays.copyOfRange(arr, l, r + 1);
        int i = l, j = m + 1, k = l;
        for (; k <= r; k++) {
            // 左半部分已经处理完
            if (i > m) {
                arr[k] = aux[j];
                j++;
            } else if (j > r) {
                arr[k] = aux[i];
                i++;
            } else if (aux[i].compareTo(aux[j]) < 0) {
                arr[k] = aux[i];
                i++;
            } else {
                arr[k] = aux[j];
                j++;
            }
        }
    }

    public static void insertionSort(Comparable[] arr, int l, int r) {
        for (int i = l + 1; i <= r; i++) {
            Comparable e = arr[i];
            int j = i;
            for (; j > l && e.compareTo(arr[j - 1]) < 0; j--) {
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
        SortTestHelper.testSort("com.issac.algo.MergeSort2", arr);
    }
}

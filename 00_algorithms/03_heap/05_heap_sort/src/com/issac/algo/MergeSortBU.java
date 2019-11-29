package com.issac.algo;

import java.util.Arrays;

/**
 * @author: ywy
 * @date: 2019-11-19
 * @desc: 归并排序 O(nlogn) 自底向上 适合链表
 */
public class MergeSortBU {
    private MergeSortBU() {
    }

    public static void sort(Comparable[] arr) {
        int n = arr.length;
        mergeSortBU(arr, n);
    }

    static void mergeSortBU(Comparable[] arr, int n) {
        for (int sz = 1; sz <= n; sz += sz) {
            for (int i = 0; i + sz < n; i += sz + sz) {
                if (sz < 16) {
                    insertionSort(arr, i, Math.min(i + 2 * sz - 1, n - 1));
                    continue;
                }
                // 对 arr[i...i+sz-1] 和 arr[i+sz...i+2*sz-1] 排序
                if (arr[i + sz - 1].compareTo(arr[i + sz]) > 0) {
                    merge(arr, i, i + sz - 1, Math.min(i + 2 * sz - 1, n - 1));
                }
            }
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
    private static void merge(Comparable[] arr, int l, int m, int r) {
        Comparable[] aux = Arrays.copyOfRange(arr, l, r + 1);
        int i = l, j = m + 1, k = l;
        for (; k <= r; k++) {
            // 左半部分已经处理完
            if (i > m) {
                arr[k] = aux[j - l];
                j++;
            } else if (j > r) {
                arr[k] = aux[i - l];
                i++;
            } else if (aux[i - l].compareTo(aux[j - l]) < 0) {
                arr[k] = aux[i - l];
                i++;
            } else {
                arr[k] = aux[j - l];
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
        SortTestHelper.testSort("com.issac.algo.MergeSortBU", arr);
    }
}

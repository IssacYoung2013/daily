package com.issac.algo;

/**
 * @author: ywy
 * @date: 2019-11-19
 * @desc: 快速排序
 */
public class QuickSort {
    private QuickSort() {
    }

    public static void sort(Comparable[] arr) {
        int n = arr.length;
        quickSort(arr, 0, n - 1);
    }

    public static void quickSort(Comparable[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int p = partition(arr, l, r);
        quickSort(arr, l, p - 1);
        quickSort(arr, p + 1, r);
    }

    public static int partition(Comparable[] arr, int l, int r) {
        Comparable e = arr[l];
        // arr[l+i...j] < v ; arr[j+1...i) > v
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (arr[i].compareTo(e) < 0) {
                j++;
                swap(arr, j, i);
            }
        }
        swap(arr, l, j);
        return j;
    }

    static void swap(Comparable[] arr, int a, int b) {
        Comparable temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args) {
        int N = 10000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 10000);
        SortTestHelper.testSort("com.issac.algo.QuickSort", arr);
    }
}

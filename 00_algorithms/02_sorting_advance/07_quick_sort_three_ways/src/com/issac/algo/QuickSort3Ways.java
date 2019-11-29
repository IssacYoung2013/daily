package com.issac.algo;

/**
 * @author: ywy
 * @date: 2019-11-19
 * @desc: 快速排序 三路
 */
public class QuickSort3Ways {
    private QuickSort3Ways() {
    }

    public static void sort(Comparable[] arr) {
        int n = arr.length;
        quickSort(arr, 0, n - 1);
    }

    public static void quickSort(Comparable[] arr, int l, int r) {
        if (r - l < 16) {
            insertionSort(arr, l, r);
            return;
        }
        // 随机
        swap(arr, l, (int) (Math.random() * (r - l + 1)) + l);

        Comparable e = arr[l];
        // arr[l+1...lt] < v ; arr[lt+1...i) = v ; arr[gt...r] > v
        int i = l + 1, lt = l, gt = r + 1;
        while (i < gt) {
            if (arr[i].compareTo(e) < 0) {
                swap(arr, lt + 1, i);
                lt++;
                i++;
            } else if (arr[i].compareTo(e) > 0) {
                swap(arr, gt - 1, i);
                gt--;
            } else {
                i++;
            }
        }
        swap(arr, l, lt);
        quickSort(arr, l, lt -1);
        quickSort(arr, gt, r);
    }

    static void swap(Comparable[] arr, int a, int b) {
        Comparable temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
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
        SortTestHelper.testSort("com.issac.algo.QuickSort3Ways", arr);
    }
}

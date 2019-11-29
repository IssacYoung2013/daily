package com.issac.algo;

/**
 * @author: ywy
 * @date: 2019-11-19
 * @desc: 快速排序 二路
 */
public class QuickSort2Ways {
    private QuickSort2Ways() {
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
        int p = partition(arr, l, r);
        quickSort(arr, l, p - 1);
        quickSort(arr, p + 1, r);
    }

    public static int partition(Comparable[] arr, int l, int r) {
        // 随机
        swap(arr, l, (int) (Math.random() * (r - l + 1)) + l);

        Comparable e = arr[l];
        // arr[l+1...i] < v ; arr[j...r) > v
        int i = l + 1, j = r;
        while (true) {
            while (i <= r && arr[i].compareTo(e) < 0) {
                i++;
            }
            while (j >= l + 1 && arr[j].compareTo(e) > 0) {
                j--;
            }
            if (i > j) {
                break;
            }
            swap(arr, i, j);
            i++;
            j--;
        }
        swap(arr,l,j);
        return j;
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
        SortTestHelper.testSort("com.issac.algo.QuickSort", arr);
    }
}

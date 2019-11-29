package com.issac.algo;

/**
 * @author: ywy
 * @date: 2019-11-19
 * @desc: 堆排序
 */
public class IndexHeapSort {
    private IndexHeapSort() {
    }

    public static void sort(Comparable[] arr) {
        int n = arr.length;
        IndexMaxHeap maxHeap = new IndexMaxHeap(n);
        for (int i = 0; i < n; i++) {
            maxHeap.insert(i,arr[i]);
        }
        for (int i = n - 1; i >= 0; i--) {
            arr[i] = maxHeap.extractMax();
        }
    }

    public static void main(String[] args) {
        int N = 10000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 10000);
        SortTestHelper.testSort("com.issac.algo.IndexHeapSort", arr);
    }
}

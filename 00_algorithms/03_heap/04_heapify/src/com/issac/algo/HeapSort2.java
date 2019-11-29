package com.issac.algo;

/**
 * @author: ywy
 * @date: 2019-11-19
 * @desc: 堆排序
 */
public class HeapSort2 {
    private HeapSort2() {
    }

    public static void sort(Comparable[] arr) {
        int n = arr.length;
        MaxHeap maxHeap = new MaxHeap(arr);
        for (int i = n - 1; i >= 0; i--) {
            arr[i] = maxHeap.extractMax();
        }
    }

    public static void main(String[] args) {
        int N = 10;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 10000);
        SortTestHelper.testSort("com.issac.algo.HeapSort2", arr);
    }
}

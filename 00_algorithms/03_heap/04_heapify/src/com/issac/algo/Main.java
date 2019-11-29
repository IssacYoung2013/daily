package com.issac.algo;

import java.util.Arrays;

/**
 * @author: ywy
 * @date: 2019-11-19
 * @desc:
 */
public class Main {
    public static void main(String[] args) {
        int N = 10000;

        // 一般测试
        System.out.println("==== 一般测试 ====");
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 10000);
        compareSort(arr);

        // 有序更强
        System.out.println("==== 有序更强 ====");
        arr = SortTestHelper.generateRandomArray(N, 0, 3);
        compareSort(arr);

        // 近乎有序
        System.out.println("==== 近乎有序 ====");
        arr = SortTestHelper.generateNearlyOrderArray(N, 10);
        compareSort(arr);
    }

    static void compareSort(Integer[] arr) {
        Integer[] arr1 = Arrays.copyOf(arr, arr.length);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        Integer[] arr3 = Arrays.copyOf(arr, arr.length);
        Integer[] arr4 = Arrays.copyOf(arr, arr.length);
        Integer[] arr5 = Arrays.copyOf(arr, arr.length);
        Integer[] arr6 = Arrays.copyOf(arr, arr.length);
        Integer[] arr7 = Arrays.copyOf(arr, arr.length);
        Integer[] arr8 = Arrays.copyOf(arr, arr.length);
        Integer[] arr9 = Arrays.copyOf(arr, arr.length);

        SortTestHelper.testSort("com.issac.algo.InsertionSort", arr1);
        SortTestHelper.testSort("com.issac.algo.MergeSort", arr2);
        SortTestHelper.testSort("com.issac.algo.MergeSort2", arr3);
        SortTestHelper.testSort("com.issac.algo.MergeSortBU", arr4);
        SortTestHelper.testSort("com.issac.algo.QuickSort", arr5);
        SortTestHelper.testSort("com.issac.algo.QuickSort2Ways", arr6);
        SortTestHelper.testSort("com.issac.algo.QuickSort3Ways", arr7);
        SortTestHelper.testSort("com.issac.algo.HeapSort1", arr8);
        SortTestHelper.testSort("com.issac.algo.HeapSort2", arr8);

    }
}

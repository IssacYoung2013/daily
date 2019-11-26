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
        Integer[] arr1 = Arrays.copyOf(arr, arr.length);
        SortTestHelper.testSort("com.issac.algo.SelectionSort", arr);
        SortTestHelper.testSort("com.issac.algo.InsertionSort", arr1);

        // 有序更强
        System.out.println("==== 有序更强 ====");
        arr = SortTestHelper.generateRandomArray(N,0,3);
        arr1 = Arrays.copyOf(arr, arr.length);
        SortTestHelper.testSort("com.issac.algo.SelectionSort", arr);
        SortTestHelper.testSort("com.issac.algo.InsertionSort", arr1);

        // 近乎有序
        System.out.println("==== 近乎有序 ====");
        arr = SortTestHelper.generateNearlyOrderArray(N,10);
        arr1 = Arrays.copyOf(arr, arr.length);
        SortTestHelper.testSort("com.issac.algo.SelectionSort", arr);
        SortTestHelper.testSort("com.issac.algo.InsertionSort", arr1);

    }
}

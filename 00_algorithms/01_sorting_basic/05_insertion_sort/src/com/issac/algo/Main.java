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
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 10000);
        Integer[] arr1 = Arrays.copyOf(arr, arr.length);
        SortTestHelper.testSort("com.issac.algo.SelectionSort", arr);
        SortTestHelper.testSort("com.issac.algo.InsertionSort", arr1);
    }
}

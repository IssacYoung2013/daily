package com.issac.algo;

import java.util.Arrays;

/**
 * @author: ywy
 * @date: 2019-11-19
 * @desc: 选择排序 O(n ^ 2)，队伍排队
 */
public class SelectionSort {
    private SelectionSort() {
    }

    public static void sort(Comparable[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[minIndex].compareTo(arr[j]) > 0) {
                    minIndex = j;
                }
            }
            if (minIndex > i) {
                swap(arr, i, minIndex);
            }
        }
    }

    static void swap(Object[] arr, int a, int b) {
        Object temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args) {
        int N = 10;
        Integer[] arr = {4, 5, 2, 1, 2, 4, 9};
        SelectionSort.sort(arr);
        System.out.println(Arrays.toString(arr));

        Float[] arr1 = {4.3f,2.8f,3.1f,5.0f,1.1f};
        SelectionSort.sort(arr1);
        System.out.println(Arrays.toString(arr1));

        Student[] arr3 = new Student[3];
        arr3[0] = new Student("tom",22);
        arr3[1] = new Student("jack",18);
        arr3[2] = new Student("issac",12);
        SelectionSort.sort(arr3);
        System.out.println(Arrays.toString(arr3));
    }
}

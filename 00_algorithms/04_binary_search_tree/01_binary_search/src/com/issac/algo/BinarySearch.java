package com.issac.algo;

/**
 * @author: ywy
 * @date: 2019-11-29
 * @desc: 二分查找法
 */
public class BinarySearch {

    public static int find(Comparable[] arr, Comparable target) {

        // arr[l..r] 查找target
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (target.compareTo(arr[m]) == 0) {
                return m;
            }
            if (target.compareTo(arr[m]) < 0) {
                r = m - 1;
            }
            if (target.compareTo(arr[m]) > 0) {
                l = m + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int N = 10000;
        Integer[] arr = new Integer[N];
        for (int i = 0; i < N; i++) {
            arr[i] = i;
        }

        for (int i = 0; i < N + 1; i++) {
            Integer v = BinarySearch.find(arr, new Integer(i));
            if (i < N) {
                assert v == i;
            } else {
                assert v == -1;
            }
        }
    }
}

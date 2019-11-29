package com.issac.algo;

/**
 * @author: ywy
 * @date: 2019-11-29
 * @desc: 二分查找法
 */
public class BinarySearch2 {

    public static int find(Comparable[] arr, int l, int r, Comparable target) {

        if (l > r) {
            return -1;
        }
        // arr[l..r] 查找target
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
        return find(arr, l, r, target);
    }

    public static void main(String[] args) {
        int N = 10000;
        Integer[] arr = new Integer[N];
        for (int i = 0; i < N; i++) {
            arr[i] = i;
        }

        for (int i = 0; i < N + 1; i++) {
            Integer v = BinarySearch2.find(arr, 0, arr.length - 1, new Integer(i));
            if (i < N) {
                assert v == i;
            } else {
                assert v == -1;
            }
        }
    }
}

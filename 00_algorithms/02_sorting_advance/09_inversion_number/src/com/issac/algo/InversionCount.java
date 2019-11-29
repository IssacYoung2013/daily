package com.issac.algo;

import java.util.Arrays;

/**
 * @author: ywy
 * @date: 2019-11-19
 * @desc: 求逆序对数量
 */
public class InversionCount {
    private InversionCount() {
    }

    public static long solve(Comparable[] arr) {
        int n = arr.length;
        return solve(arr, 0, n - 1);
    }

    static long solve(Comparable[] arr, int l, int r) {
        if (l >= r) {
            return 0;
        }
        // 避免溢出
        int m = (r - l) / 2 + l;
        long res1 = solve(arr, l, m);
        long res2 = solve(arr, m + 1, r);
        return res1 + res2 + merge(arr, l, m, r);
    }

    /**
     * (l,m)(m+1,r)归并排序
     *
     * @param arr
     * @param l
     * @param m
     * @param r
     */
    private static long merge(Comparable[] arr, int l, int m, int r) {
        long inversionCount = 0;
        Comparable[] aux = Arrays.copyOfRange(arr, l, r + 1);
        int i = l, j = m + 1, k = l;
        for (; k <= r; k++) {
            // 左半部分已经处理完
            if (i > m) {
                arr[k] = aux[j - l];
                j++;
            } else if (j > r) {
                arr[k] = aux[i - l];
                i++;
            } else if (aux[i - l].compareTo(aux[j - l]) < 0) {
                arr[k] = aux[i - l];
                i++;
            } else {
                arr[k] = aux[j - l];
                j++;
                inversionCount += (m -i +1);
            }
        }
        return inversionCount;
    }

    public static void main(String[] args) {
        int N = 4;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 10000);
        SortTestHelper.printArray(arr);
        System.out.println("Test Inversion Count Of Random Array:" + solve(arr));

    }
}

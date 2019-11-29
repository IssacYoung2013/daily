package com.issac.algo;

/**
 * @author: ywy
 * @date: 2019-11-19
 * @desc: 取最n数
 */
public class Selection {
    private Selection() {
    }

    public static Comparable solve(Comparable[] arr, int index) {
        int n = arr.length;
        return solve(arr, 0, n - 1, index - 1);
    }

    public static Comparable solve(Comparable[] arr, int l, int r, int index) {
        if (l >= r) {
            return arr[l];
        }
        int p = partition(arr, l, r);
        if (index == p) {
            return arr[p];
        } else if (index < p) {
            return solve(arr, l, p - 1, index);
        } else {
            return solve(arr, p + 1, r, index);
        }
    }

    public static int partition(Comparable[] arr, int l, int r) {
        Comparable e = arr[l];
        // arr[l+i...j] < v ; arr[j+1...i) > v
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (arr[i].compareTo(e) < 0) {
                j++;
                swap(arr, j, i);
            }
        }
        swap(arr, l, j);
        return j;
    }

    static void swap(Comparable[] arr, int a, int b) {
        Comparable temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args) {
        int N = 10;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 10000);
        SortTestHelper.printArray(arr);
        int index = 1;
        System.out.println("Test Search NO." + index + " Index Element Random Array:" + solve(arr, index));
    }
}

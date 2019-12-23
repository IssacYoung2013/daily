package com.issac.algo;

/**
 * @author: ywy
 * @date: 2019-12-18
 * @desc:
 */
public class BinarySearch {

    public static int find(Comparable[] arr, Comparable target) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int m = (r - l) / 2 + l;
            if (target.compareTo(arr[m]) == 0) {
                return m;
            } else if (target.compareTo(arr[m]) < 0) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return -1;
    }

    public static int floor(Comparable[] arr, Comparable target) {
        // 查找比target小的最大索引
        int l = -1, r = arr.length - 1;
        while (l < r) {
            int m = (r - l + 1) / 2 + l;
            if (target.compareTo(arr[m]) > 0) {
                l = m;
            } else if (target.compareTo(arr[m]) <= 0) {
                r = m - 1;
            }
        }
        assert l == r;
        if (l + 1 < arr.length && arr[l + 1] == target) {
            return l + 1;
        }
        return l;
    }

    public static int ceil(Comparable[] arr, Comparable target) {
        // 查找比target大的最小索引
        int l = 0, r = arr.length - 1;
        while (l < r) {
            int m = (r - l) / 2 + l;
            if (target.compareTo(arr[m]) < 0) {
                r = m;
            } else if (target.compareTo(arr[m]) >= 0) {
                l = m + 1;
            }
        }
        assert l == r;
        if (r - 1 >= 0 && arr[r - 1] == target) {
            return r - 1;
        }
        return r;
    }

    public static void main(String[] args) {
        Integer arr[] = new Integer[]{1, 1, 1, 2, 2, 2, 2, 2, 4, 4, 5, 5, 5, 6, 6, 6};
        for (int i = 0; i <= 8; i++) {
            int floorIndex = floor(arr, i);
            System.out.println("The floor index of " + i + " is " + floorIndex);
            if (floorIndex >= 0 && floorIndex < arr.length) {
                System.out.println("The floor value is " + arr[floorIndex]);
            }
            System.out.println();
            int ceilIndex = ceil(arr, i);
            System.out.println("The ceil index of " + i + " is " + ceilIndex);
            if (ceilIndex >= 0 && ceilIndex < arr.length) {
                System.out.println("The ceil value is " + arr[ceilIndex]);
            }
            System.out.println();
        }
    }

}

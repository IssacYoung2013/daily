package com.issac.algo;

/**
 * @author: ywy
 * @date: 2019-11-29
 * @desc:
 */
public class Main {

    public static void shuffle(Object[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int pos = (int) (Math.random() * (i + 1));
            Object t = arr[i];
            arr[i] = arr[pos];
            arr[pos] = t;
        }
    }

    public static void main(String[] args) {
        BST<Integer, Integer> bst = new BST<>();

        int N = 10;
        int M = 100;
        for (int i = 0; i < N; i++) {
            int key = (int) (Math.random() * N);
            bst.insert(key, key);
        }

        Integer[] order = new Integer[N];
        for (int i = 0; i < N; i++) {
            order[i] = new Integer(i);
        }
        shuffle(order);
        System.out.println("BST size:" + bst.size());
        for (int i = 0; i < N; i++) {
            if (bst.contain(order[i])) {
                bst.remove(order[i]);
                System.out.println("After remove size:" + bst.size());
            }
        }
        System.out.println("BST size:" + bst.size());
    }
}

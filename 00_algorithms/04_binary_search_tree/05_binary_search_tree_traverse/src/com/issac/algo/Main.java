package com.issac.algo;

/**
 * @author: ywy
 * @date: 2019-11-29
 * @desc:
 */
public class Main {

    public static void main(String[] args) {
        BST<Integer, Integer> bst = new BST<>();

        int N = 10;
        int M = 100;
        for (int i = 0; i < N; i++) {
            int key = (int) (Math.random() * M);
            bst.insert(key, key);
            System.out.print(key + " ");
        }
        System.out.println();
        System.out.println("preOrder:");
        bst.preOrder();
        System.out.println();
        System.out.println("inOrder:");
        bst.inOrder();
        System.out.println();
        System.out.println("postOrder:");
        bst.postOrder();
        System.out.println();
    }
}

package com.issac.algo;

/**
 * @author: ywy
 * @date: 2019-11-29
 * @desc: 二分搜索树
 */
public class BST<Key extends Comparable<Key>, Value> {

    private class Node {
        private Key key;
        private Value value;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }

        private Node left;
        private Node right;
    }

    private Node root;
    private int count;

    public BST() {
        root = null;
        count = 0;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }
}

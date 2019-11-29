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

    public void insert(Key key, Value value) {
        root = insert(root, key, value);
    }

    /**
     * 向以Node为根的插入节点
     *
     * @param node
     * @param key
     * @param value
     * @return
     */
    private Node insert(Node node, Key key, Value value) {
        if (node == null) {
            count++;
            return new Node(key, value);
        }
        if (node.key.compareTo(key) == 0) {
            node.value = value;
        } else if (node.key.compareTo(key) < 0) {
            node.right = insert(node.right, key, value);
        } else {
            node.left = insert(node.left, key, value);
        }
        return node;
    }

    public boolean contain(Key key) {
        return contain(root, key);
    }

    private boolean contain(Node node, Key key) {
        if (node == null) {
            return false;
        }
        if (node.key.compareTo(key) == 0) {
            return true;
        } else if (node.key.compareTo(key) < 0) {
            return contain(node.right, key);
        } else if (node.key.compareTo(key) > 0) {
            return contain(node.left, key);
        }
        return false;
    }

    public Value search(Key key) {
        return search(root,key);
    }

    private Value search(Node node, Key key) {
        if(node == null) {
            return null;
        }
        if (node.key.compareTo(key) == 0) {
            return node.value;
        } else if (node.key.compareTo(key) < 0) {
            return search(node.right, key);
        } else if (node.key.compareTo(key) > 0) {
            return search(node.left, key);
        }
        return null;
    }
}

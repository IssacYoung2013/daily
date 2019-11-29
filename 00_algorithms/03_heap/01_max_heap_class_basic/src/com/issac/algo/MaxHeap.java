package com.issac.algo;

/**
 * @author: ywy
 * @date: 2019-11-26
 * @desc:
 */
public class MaxHeap<Item> {

    private Item[] data;

    private int count;

    public MaxHeap(int count) {
        data = (Item[]) new Object[count];
        this.count = 0;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public static void main(String[] args) {
        MaxHeap<Integer> maxHeap = new MaxHeap<>(100);
        System.out.println(maxHeap.size());
    }
}

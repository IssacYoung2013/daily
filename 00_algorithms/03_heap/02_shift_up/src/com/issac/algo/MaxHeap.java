package com.issac.algo;

/**
 * @author: ywy
 * @date: 2019-11-26
 * @desc:
 */
public class MaxHeap<Item extends Comparable> {

    protected Item[] data;

    protected int count;

    private int capacity;

    public MaxHeap(int capacity) {
        this.capacity = capacity;
        data = (Item[]) new Comparable[capacity];
        this.count = 0;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    protected void insert(Item item) {
        assert count + 1 <= capacity;
        data[count + 1] = item;
        count++;
        shiftUp(count);
    }

    private void shiftUp(int k) {
        while (k > 1 && data[k / 2].compareTo(data[k]) < 0) {
            swap(k / 2, k);
            k /= 2;
        }
    }

    private void swap(int a, int b) {
        Item temp = data[a];
        data[a] = data[b];
        data[b] = temp;
    }

    public static void main(String[] args) {
        MaxHeap<Integer> maxHeap = new MaxHeap<>(10);
        int N = 10;
        int M = 50;
        for (int i = 0; i < N; i++) {
            maxHeap.insert(((int) Math.random() * M));
        }


    }
}

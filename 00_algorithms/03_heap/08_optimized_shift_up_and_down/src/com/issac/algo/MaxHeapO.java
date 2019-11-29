package com.issac.algo;

/**
 * @author: ywy
 * @date: 2019-11-26
 * @desc:
 */
public class MaxHeapO<Item extends Comparable> {

    protected Item[] data;

    protected int count;

    private int capacity;

    public MaxHeapO(int capacity) {
        this.capacity = capacity;
        data = (Item[]) new Comparable[capacity + 1];
        this.count = 0;
    }

    public MaxHeapO(Item[] arr) {
        int n = arr.length;
        this.data = (Item[]) new Comparable[n + 1];
        capacity = n;
        for (int i = 0; i < n; i++) {
            data[i + 1] = arr[i];
        }
        count = n;
        for (int i = count / 2; i > 0; i--) {
            shiftDown(i);
        }
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
        Item e = data[k];
        int j = k;
        while (k > 1 && data[k / 2].compareTo(e) < 0) {
            data[k] = data[k / 2];
            k /= 2;
        }
        data[k] = e;
    }

    public Item extractMax() {
        assert count > 0;
        Item ret = data[1];
        swap(1, count);
        count--;
        shiftDown(1);
        return ret;
    }

    private void shiftDown(int k) {
        Item e = data[k];
        while (2 * k <= count) {
            int j = 2 * k;
            if (j + 1 <= count && data[j + 1].compareTo(data[j]) > 0) {
                j++;
            }
            if (e.compareTo(data[j]) >= 0) {
                break;
            }
            data[k] = data[j];
            k = j;
        }
        data[k] = e;
    }

    private void swap(int a, int b) {
        Item temp = data[a];
        data[a] = data[b];
        data[b] = temp;
    }

    public static void main(String[] args) {
        PrintableMaxHeap maxHeap = new PrintableMaxHeap(100);
        int N = 10;
        int M = 50;
//        for (int i = 0; i < N; i++) {
//            maxHeap.insert(((int) (Math.random() * M)));
//        }
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100);
        maxHeap = new PrintableMaxHeap(arr);
        maxHeap.treePrint();
        for (int i = N - 1; i >= 0; i--) {
            System.out.print(maxHeap.extractMax() + " ");
        }
    }
}

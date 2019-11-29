package com.issac.algo;

/**
 * @author: ywy
 * @date: 2019-11-26
 * @desc:
 */
public class IndexMaxHeap<Item extends Comparable> {

    protected Item[] data;

    private int[] indexes;

    private int[] reverse;

    protected int count;

    private int capacity;

    public IndexMaxHeap(int capacity) {
        this.capacity = capacity;
        data = (Item[]) new Comparable[capacity + 1];
        indexes = new int[capacity + 1];
        reverse = new int[capacity + 1];
        for (int i = 0; i <= capacity; i++) {
            reverse[i] = 0;
        }
        this.count = 0;
    }

    public IndexMaxHeap(Item[] arr) {
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

    protected void insert(int i, Item item) {
        assert count + 1 <= capacity;
        assert i > 0 && i + 1 < capacity;
        i += 1;
        data[i] = item;
        indexes[count + 1] = i;
        reverse[i] = count + 1;
        count++;
        shiftUp(count);
    }

    private void shiftUp(int k) {
        while (k > 1 && data[indexes[k / 2]].compareTo(data[indexes[k]]) < 0) {
            swap(k / 2, k);
            k /= 2;
        }
    }

    public Item extractMax() {
        assert count > 0;
        Item ret = data[indexes[1]];
        swap(1, count);
        count--;
        shiftDown(1);
        return ret;
    }

    public int extractMaxIndex() {
        assert count > 0;
        int ret = indexes[1] - 1;
        swap(1, count);
        count--;
        shiftDown(1);
        return ret;
    }

    public Item getItem(int i) {
        assert contain(i);
        return data[i + 1];
    }

    public void change(int i, Item newItem) {
        assert contain(i);
        i += 1;
        data[i] = newItem;
//        for (int j = 1; j <= count; j++) {
//            if(indexes[j] == i) {
//                shiftUp(j);
//                shiftDown(j);
//                return;
//            }
//        }
        int j = reverse[i];
        shiftUp(j);
        shiftDown(j);
    }

    private boolean contain(int i) {
        return i > 0 && i + 1 < capacity && reverse[i + 1] != 0;
    }

    private void shiftDown(int k) {
        while (2 * k <= count) {
            int j = 2 * k;
            if (j + 1 <= count && data[indexes[j + 1]].compareTo(data[indexes[j]]) > 0) {
                j++;
            }
            if (data[indexes[k]].compareTo(data[indexes[j]]) >= 0) {
                break;
            }
            swap(k, j);
            k = j;
        }
    }

    private void swap(int a, int b) {
        int temp = indexes[a];
        indexes[a] = indexes[b];
        reverse[indexes[b]] = b;
        reverse[indexes[a]] = a;
        indexes[b] = temp;
    }

    public static void main(String[] args) {
        IndexMaxHeap maxHeap = new IndexMaxHeap(100);
        int N = 10;
        int M = 50;
//        for (int i = 0; i < N; i++) {
//            maxHeap.insert(((int) (Math.random() * M)));
//        }
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 1000);
        for (int i = N - 1; i >= 0; i--) {
            System.out.print(maxHeap.extractMax() + " ");
        }
    }
}

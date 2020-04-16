package com.issac.discruptor.ablitity;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author: ywy
 * @date: 2020-02-25
 * @desc:
 */
public class ArrayBlockingQueueTest {
    public static void main(String[] args) {
        final ArrayBlockingQueue<Data> queue = new ArrayBlockingQueue<Data>(100000000);
        long startTime = System.currentTimeMillis();
        new Thread(() -> {
            long i = 0;
            while (i < Constants.EVENT_NUM_FM) {
                Data data = new Data(i, "c" + i);
                try {
                    queue.put(data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
        }).start();

        new Thread(() -> {
            int k = 0;
            while (k < Constants.EVENT_NUM_FM) {
                try {
                    queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                k++;
            }
            long endTime = System.currentTimeMillis();
            System.out.println("ArryayBlockingQueue costTime = " + (endTime - startTime));
        }).start();

    }
}

package com.issac.discruptor.ablitity;

import com.lmax.disruptor.EventHandler;

/**
 * @author: ywy
 * @date: 2020-02-27
 * @desc:
 */
public class DataConsumer implements EventHandler<Data> {

    private long startTime;
    private int i;

    public DataConsumer() {
        startTime = System.currentTimeMillis();
    }

    @Override
    public void onEvent(Data data, long seq, boolean bool) throws Exception {
        i++;
        if (i == Constants.EVENT_NUM_FM) {
            long endTime = System.currentTimeMillis();
            System.out.println("Disruptor costTime = " + (endTime - startTime) + "ms");
        }
    }
}

package com.issac.icu.threadPool;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: ywy
 * @date: 2019-12-25
 * @desc:
 */
public class ThreadVs {
    /**
     * 老的处理方式
     */
    @Test
    public void oldHandle() throws InterruptedException {

        /**
         * 使用循环模拟许多用户请求的场景
         */
        for (int request = 1; request <= 100; request++) {
            new Thread(() -> {
                System.out.println("文档处理开始！");
                try {
                    // 将Word转换成PDF格式：处理时长很长的耗时过程
                    Thread.sleep(1000L * 30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("文档处理结束！");
            }).start();
        }

        Thread.sleep(1000L * 1000);
    }

    /**
     * 新的处理方式
     */
    @Test
    public void newHandle() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        /**
         * 使用循环模拟许多用户请求的场景
         */
        for (int request = 1; request <= 100; request++) {
            executorService.execute(() -> {
                System.out.println("文档处理开始！");
                try {
                    // 将Word转换成PDF格式：处理时长很长的耗时过程
                    Thread.sleep(1000L * 30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("文档处理结束！");
            });
        }

        Thread.sleep(1000L * 1000);
        executorService.shutdown();
    }
}

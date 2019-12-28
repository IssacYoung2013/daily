package com.issac.icu.threadPool;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author: ywy
 * @date: 2019-12-25
 * @desc:
 */
public class RunTest {
    @Test
    public void submitTest() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<Integer> future = executorService.submit(() -> {
            //
            Thread.sleep(1000L * 10);
            return 2 * 5;
        });

        // 阻塞
        Integer num = future.get();
        System.out.println("执行结果："+ num);
    }

    @Test
    public void executeTest() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->{
            try {
                Thread.sleep(1000L * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("执行结果："+ 10);
        });
        Thread.sleep(1000L * 60);
    }
}

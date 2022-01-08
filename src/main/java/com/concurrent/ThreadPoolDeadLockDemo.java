package com.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/19
 */
public class ThreadPoolDeadLockDemo {


    public static void main(String[] args) throws InterruptedException {
//        threadPoolDeadLock1();
        ExecutorService threadPool = Executors.newFixedThreadPool(1);
        threadPool.execute(() -> {
            System.out.println("outer task");
            try {
                String s = threadPool.submit(() -> "hello").get();
                System.out.println(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    private static void threadPoolDeadLock1() throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        CountDownLatch latch1 = new CountDownLatch(2);
        for (int i = 0; i < 2; i++) {
            threadPool.execute(() -> {
                System.out.println("task outer run");
                CountDownLatch latch2 = new CountDownLatch(2);
                for (int j = 0; j < 2; j++) {
                    threadPool.execute(() -> {
                        System.out.println("task inner run");
                        latch2.countDown();
                    });
                }
                try {
                    latch2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch1.countDown();
            });
        }
        latch1.await();
        System.out.println("end");
    }
}

package com.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/9/28
 * 信号量实现线程安全的加法器
 */
public class SafeAdderWithSemaphore {

    private long value;

    private final Semaphore semaphore = new Semaphore(1);

    public void addOne() {
        try {
            semaphore.acquire();
            value++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    public long get() {
        return value;
    }

    public static void main(String[] args) throws InterruptedException {
        SafeAdderWithSemaphore adder = new SafeAdderWithSemaphore();
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    adder.addOne();
                }
            });
            threadList.add(thread);
        }
        for (Thread thread : threadList) {
            thread.start();
        }
        for (Thread thread : threadList) {
            thread.join();
        }
        System.out.println(adder.get());
    }
}

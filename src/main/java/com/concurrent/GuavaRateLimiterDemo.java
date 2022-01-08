package com.concurrent;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/25
 */
public class GuavaRateLimiterDemo {

    private static long prev = System.currentTimeMillis();

    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(2);
        ExecutorService threadPool = Executors.newFixedThreadPool(1);
        for (int i = 0; i < 20; i++) {
            rateLimiter.acquire();
            threadPool.execute(() -> {
                long curr = System.currentTimeMillis();
                System.out.println("process in after " + (curr - prev));
                prev = curr;
            });
        }
    }
}

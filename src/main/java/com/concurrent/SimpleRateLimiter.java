package com.concurrent;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/25
 * 桶的容量为1的令牌桶
 */
public class SimpleRateLimiter {

    long nextTokenTime = System.nanoTime();

    long interval = 1000_000_000;

    private static long prev = System.currentTimeMillis();

    public void acquire() {
        long acquireTime = System.nanoTime();
        long nextTokenTime = getNextTokenTime(acquireTime);
        long waitTime = Math.max(nextTokenTime - acquireTime, 0);
        if (waitTime > 0) {
            try {
                TimeUnit.NANOSECONDS.sleep(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 用锁保护nextTokenTime的修改
     * 返回下一个令牌的的生成时间
     * @param acquireTime 请求时间
     */
    private synchronized long getNextTokenTime(long acquireTime) {
        // 请求时间再生成时间之后
        if (acquireTime > nextTokenTime) {
            nextTokenTime = acquireTime;
        }
        // 获取下一个令牌的生成时间
        long tokenTime = nextTokenTime;
        // 当前时间的令牌已经被占了 修改时间
        nextTokenTime += interval;
        return tokenTime;
    }

    public static void main(String[] args) {
        SimpleRateLimiter limiter = new SimpleRateLimiter();
        ExecutorService threadPool = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++) {
            threadPool.execute(() -> {
                limiter.acquire();
                long curr = System.currentTimeMillis();
                System.out.println(Thread.currentThread().getName() + " process in after " + (curr - prev));
                prev = curr;
            });
        }
    }
}

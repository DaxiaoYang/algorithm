package com.concurrent;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/9/22
 */
public class SafeAdder {

    private long count;

    /**
     * get加锁是为可见性
     */
    public synchronized long get() {
        return count;
    }

    /**
     * synchronize保证了原子性和可见性(happens-before原则推导出)
     */
    public synchronized void add(long amount) {
        count += amount;
    }
}

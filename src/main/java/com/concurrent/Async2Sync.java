package com.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/9/28
 * 异步转同步
 */
public class Async2Sync {

    private static final Lock lock = new ReentrantLock();

    private static final Condition condition = lock.newCondition();

    private static int res;

    public static void main(String[] args) {
        System.out.println(getSyncRes());
    }

    private static int getSyncRes() {
        new Thread(() -> {
            try {
                // 模拟计算
                Thread.sleep(5000);
                res = 1000;
                // 等待条件前需获取锁 想想管程模型
                lock.lock();
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();
        try {
            lock.lock();
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return res;
    }
}

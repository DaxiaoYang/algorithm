package com.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/9/28
 *
 */
public class LimitedObjectPool<T> {

    private List<T> objectPool = new Vector<>();

    private Semaphore semaphore;

    public LimitedObjectPool(int size, T t) {
        for (int i = 0; i < size; i++) {
            objectPool.add(t);
        }
        semaphore = new Semaphore(size);
    }

    public <R> R execute(Function<T, R> function) {
        T object = null;
        R res = null;
        try {
            semaphore.acquire();
            object = objectPool.remove(objectPool.size() - 1);
            res = function.apply(object);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            objectPool.add(object);
            semaphore.release();
        }
        return res;
    }

    public static void main(String[] args) {
        LimitedObjectPool<Integer> pool = new LimitedObjectPool<>(4, 1);
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            final int a = i;
            threads.add(new Thread(() -> {
                pool.execute(num -> {
                    System.out.println(num);
                    return num.toString();
                });
            }));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

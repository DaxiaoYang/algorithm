package com.concurrent;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/9/25
 */
public class MyBlockingQueue<T> {

    private Queue<T> queue;

    private Lock lock = new ReentrantLock();

    private int capacity;

    /**
     * 条件为队列不空
     */
    private Condition notEmpty = lock.newCondition();

    /**
     * 条件为队列不满
     */
    private Condition notFull = lock.newCondition();


    public MyBlockingQueue(int capacity) {
        queue = new ArrayDeque<>(capacity);
        this.capacity = capacity;
    }

    public T take() {
        lock.lock();
        T ret = null;
        try {
            System.out.println(Thread.currentThread().getName() + " take");
             while (queue.size() == 0) {
                 notEmpty.await();
             }
             ret = queue.poll();
             notFull.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return ret;
    }

    public void add(T t) {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " add");
            while (queue.size() == capacity) {
                notFull.await();
            }
            queue.add(t);
            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        MyBlockingQueue<String> myBlockingQueue = new MyBlockingQueue<>(4);
        new Thread(() -> {
            String take = myBlockingQueue.take();
            System.out.println(take);
        }).start();
        new Thread(() -> {
            myBlockingQueue.add("123");
        }).start();
    }
}

package com.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/4
 */
public class Cache<K, V> {

    private Map<K, V> map = new HashMap<>();

    private ReadWriteLock lock = new ReentrantReadWriteLock();

    private Lock writeLock = lock.writeLock();

    private Lock readLock = lock.readLock();


    public void put(K k, V v) {
        writeLock.lock();
        try {
            map.put(k, v);
        } finally {
            writeLock.unlock();
        }
    }

    public V get(K k) {
        readLock.lock();
        V v;
        try {
            v = map.get(k);
        } finally {
            readLock.unlock();
        }
        if (v != null) {
            return v;
        }
        writeLock.lock();
        try {
            // double check 避免几个写线程都在写锁阻塞 只有一个获取并刷新了 之后的又要刷新
            v = map.get(k);
            if (v != null) {
                return v;
            }
            // 从db加载数据 map.put(k, v);
            //
        } finally {
            writeLock.unlock();
        }
        return v;
    }
}

package com.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.StampedLock;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/5
 */
public class CacheStamped<K, V> {

    private Map<K, V> map = new HashMap<>();

    private StampedLock lock = new StampedLock();

    public void put(K k, V v) {
        long stamp = lock.writeLock();
        try {
            map.put(k, v);
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    public V get(K k) {
        long stamp = lock.tryOptimisticRead();
        V ret = map.get(k);
        if (!lock.validate(stamp)) {
            stamp = lock.readLock();
            try {
                ret = map.get(k);
            } finally {
                lock.unlockRead(stamp);
            }
        }
        return ret;
    }
}

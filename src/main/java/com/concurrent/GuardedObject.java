package com.concurrent;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/15
 */

public class GuardedObject<T> {

    private T obj;

    private Lock lock = new ReentrantLock();

    private Condition assigned = lock.newCondition();

    private static final int WAIT_SEC = 5;

    private static final Map<Object, GuardedObject> map = new ConcurrentHashMap<>();

    private GuardedObject() {}

    public static <K> GuardedObject getInstance(K key) {
        GuardedObject instance = new GuardedObject<>();
        map.put(key, instance);
        return instance;
    }

    public static <K, T> void fireEvent(K key, T obj) {
        GuardedObject guardedObject = map.remove(key);
        if (guardedObject != null) {
            guardedObject.onChanged(obj);
        }
    }

    public T getObj(Predicate<T> predicate) {
        lock.lock();
        try {
            while (!predicate.test(obj)) {
                assigned.await(WAIT_SEC, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            throw new RuntimeException();
        } finally {
            lock.unlock();
        }
        return obj;
    }

    public void onChanged(T obj) {
        lock.lock();
        try {
            this.obj = obj;
            assigned.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        String id = UUID.randomUUID().toString();
        GuardedObject instance = GuardedObject.getInstance(id);
        new Thread(() -> {
            // 模拟异步回调
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            GuardedObject.fireEvent(id, "回调的信息");
        }).start();
        Object obj = instance.getObj(Objects::nonNull);
        System.out.println(obj);
    }
}

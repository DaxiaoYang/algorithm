package com.concurrent.threadlocal;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/9/16
 */
public class ThreadId {

    private static final AtomicLong nextId = new AtomicLong(0);

    private static final ThreadLocal<Long> threadId = ThreadLocal.withInitial(nextId::getAndIncrement);

    public static long get() {
        return threadId.get();
    }
}

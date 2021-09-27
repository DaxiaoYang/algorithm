package com.concurrent;

import java.util.HashSet;
import java.util.Set;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/9/23
 */
public class AccountAllocator {

    /**
     * 已经被分配的对象（锁）
     */
    private Set<Account> allocatedAccount = new HashSet<>();

    public synchronized boolean apply(Account from, Account to) {
        while (allocatedAccount.contains(from) || allocatedAccount.contains(to)) {
            try {
                // 获取锁之后 如果条件不满足 释放锁 进行等待(进入该互斥锁的等待队列)
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        allocatedAccount.add(from);
        allocatedAccount.add(to);
        return true;
    }

    public synchronized void free(Account from, Account to) {
        allocatedAccount.remove(from);
        allocatedAccount.remove(to);
        // 通知等待队列中的线程 条件曾经在某个时间点上满足过
        notifyAll();
    }
}

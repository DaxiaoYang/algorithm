package com.concurrent;

import java.util.Objects;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/9/22
 */
public class Account {

    private int id;

    private long balance;

    private AccountAllocator accountAllocator;

    public Account(int id, AccountAllocator accountAllocator) {
        this.id = id;
        this.accountAllocator = accountAllocator;
    }

//        public void transfer(Account target, long amount) {
//        Account firstLock = target;
//        Account secondLock = this;
//        if (id < target.id) {
//            firstLock = this;
//            secondLock = target;
//        }
//        synchronized (firstLock) {
//            synchronized (secondLock) {
//                balance -= amount;
//                target.balance += amount;
//            }
//        }
//    }


    public void transfer(Account target, long amount) {
        while (!accountAllocator.apply(this, target)) {
            // 循环直到获取两个对象的锁
        }
        try {
            synchronized (this) {
                synchronized (target) {
                    balance -= amount;
                    target.balance += amount;
                }
            }
        } finally {
            accountAllocator.free(this, target);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

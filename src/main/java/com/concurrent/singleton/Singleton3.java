package com.concurrent.singleton;

// 懒加载
public final class Singleton3 {

    private Singleton3() {}

    private static Singleton3 INSTANCE;

    // 由锁（锁对象为Singleton3.class）来保证原子性和可见性
    // 缺点是之后每次获取对象时都需要进行同步
    public static synchronized Singleton3 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Singleton3();
        }
        return INSTANCE;
    }
}

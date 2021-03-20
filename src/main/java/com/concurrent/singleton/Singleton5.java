package com.concurrent.singleton;

// 内部类懒加载
public final class Singleton5 {

    private Singleton5() {}

    private static Singleton5 INSTANCE;

    public static Singleton5 getInstance() {
        return LazyHolder.INSTANCE;
    }

    // 内部类在第一次被调用时加载
    private static class LazyHolder {
        static final Singleton5 INSTANCE = new Singleton5();
    }
}

package com.concurrent.singleton;

import java.io.Serializable;

// 类加final防止子类覆盖方法 破坏单例性质
public final class Singleton1 implements Serializable {
    // 构造方法私有 但仍可以通过反射创建对象
    private Singleton1() {}
    // 类加载时初始化 由JVM保证线程安全(立即加载)
    private static final Singleton1 INSTANCE = new Singleton1();

    public static Singleton1 getInstance() {
        return INSTANCE;
    }

    // 防止反序列化破坏单例
    private Object readResolve() {
        return INSTANCE;
    }
}

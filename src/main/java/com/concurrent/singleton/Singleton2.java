package com.concurrent.singleton;

/**
 * 枚举类实现单例 无法用反射和反序列化破坏单例
 */
public enum Singleton2 {
    // 本质上时枚举类的静态成员变量(立即加载) public static final ...
    INSTANCE;
}

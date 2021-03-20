package com.concurrent.singleton;

// DCL 懒加载
public final class Singleton4 {

    private Singleton4() {}

    private static volatile Singleton4 INSTANCE;

    // 加锁只存在于第一次创建单例对象时 之后的获取都不用加锁
    public static Singleton4 getInstance() {
        if (INSTANCE != null) {
            return INSTANCE;
        }
        // double check的原因时因为可能由多个线程都会到达这里
        // 如：线程t1 t2 t1获得锁 进入设置后 t2也会进入同步代码块
        synchronized (Singleton4.class) {
            // 所以这里不再检验一遍就无法保证单例
            if (INSTANCE != null) {
                return INSTANCE;
            }
            // volatile保证了字节码执行的有序性（通过读屏障和写屏障）
            // 可以避免出现先赋值 后执行构造方法的情况（此时会导致其他线程看到未构造完成的对象）
            INSTANCE = new Singleton4();
            return INSTANCE;
        }
    }
}

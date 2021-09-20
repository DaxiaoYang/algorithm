package com.concurrent.threadlocal;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/9/15
 */
public class ThreadLocalTest {

    /**
     * 设置为静态成员变量即可
     * 使用静态工厂方法withInitial() 生成一个重写了initialValue的类
     */
    private static ThreadLocal<Integer> cache = ThreadLocal.withInitial(() -> 0);

    public static void main(String[] args) {
        Integer integer = cache.get();
        cache.set(1);
        cache.remove();
    }

}

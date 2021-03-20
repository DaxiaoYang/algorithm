package com.concurrent;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class MyAtomicInteger {

    private volatile int value;
    private static Unsafe unsafe;
    private static long offset;

    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);
            offset = unsafe.objectFieldOffset(MyAtomicInteger.class.getDeclaredField("value"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getValue() {
        return value;
    }

    public void increment(int num) {
        while (true) {
            // 取出原来的数 A
            int prev = value;
            // 对 A 进行计算得到 B
            int next = prev + num;
            // 判断A与现在的数是否相等 如果相等说明其他线程没有修改过该数（会有ABA问题）则将数的值更新为B
            if (unsafe.compareAndSwapInt(this, offset, prev, next)) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        MyAtomicInteger atomicInteger = new MyAtomicInteger();
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                atomicInteger.increment(10);
            }).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(atomicInteger.getValue());
    }
}

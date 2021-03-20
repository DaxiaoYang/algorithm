package com.concurrent;

public class VolatileTest {

    private volatile static boolean stop;

    private static int sum;

    public static void main(String[] args) {
        new Thread(() -> {
            while (!stop) {
                //
            }
            System.out.println(Thread.currentThread() + "线程结束");
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stop = true;
        System.out.println("设置stop=true");

    }

    public void write() {
        sum++;
        stop = true;
        // 写屏障：在写屏障之前 所有对共享变量的改动 都会写入到主内存中 且写屏障之前的指令不会在写屏障之后执行
    }

    public void read() {
        // 读屏障： 在读屏障之后 所有对共享变量的读取 都要从主内存中读 且读屏障之后的指令不会在读屏障之前执行
        if (!stop) {
            int read = sum;
        }
    }
}

package com.concurrent;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/9/20
 * 1 单线程中控制流前面的操作先于后面的操作
 * 2 对`volatile`变量的写操作先于对其的读操作
 * 3 对同一个锁的解锁操作先于对一个锁的加锁操作
 * 4 happens-before具有传递性
 * 5 一个线程的`thread.start()`方法先于该线程的执行
 * 6 线程的执行先于`thread.join()`
 */
public class HappensBeforeDemo {

    private static int globalVar;

    private static volatile boolean flag;

    /**
     * 写线程中执行
     */
    public static void write() {
        // 1.单线程中 对globalVar的赋值的结果 对 flag=true这里可见
        globalVar = 1;
        flag = true;
    }

    /**
     * 读线程中执行
     */
    public static void read() {
        // 2. flag的写 对flag的读可见
        System.out.println("flag:" + flag);
        // 3. 传递性：globalVar = 1; 先于 flag = true 先于 System.out.println("flag:" + flag);
        // 所以这里能看到globalVar写的结果
        System.out.println("var:" + globalVar);
    }

    private synchronized static void syncAdd() {
        // 对global变量的写 先于解锁 且解锁时会强制刷新数据到缓存
        globalVar += 10;
    }

    public static void main(String[] args) throws InterruptedException {
//        test123();
//        test5();
//        test5();
//        test6();
    }

    private static void test6() throws InterruptedException {
        Thread thread = new Thread(HappensBeforeDemo::write);
        thread.start();
        thread.join();
        read();
    }

    private static void test4() throws InterruptedException {
        new Thread(HappensBeforeDemo::syncAdd).start();
        Thread.sleep(100);
        System.out.println("var:" + globalVar);
    }

    private static void test5() {
        // 测试 5
        globalVar = 100;
        new Thread(HappensBeforeDemo::read).start();
    }

    private static void test123() throws InterruptedException {
        // 测试 1 2
        new Thread(HappensBeforeDemo::write).start();
        Thread.sleep(1000);
        new Thread(HappensBeforeDemo::read).start();
    }
}

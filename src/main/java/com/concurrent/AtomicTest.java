package com.concurrent;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/9/19
 */
public class AtomicTest {

//    private static int count = 0;
//
//    public static void main(String[] args) throws InterruptedException {
//        Runnable runnable = () -> {
//            for (int i = 0; i < 10000; i++) {
//                count++;
//            }
//        };
//        Thread t1 = new Thread(runnable);
//        Thread t2 = new Thread(runnable);
//        t1.start();
//        t2.start();
//
//        t1.join();
//        t2.join();
//        System.out.println(count);
//    }

    private static boolean stop;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (!stop) {

                System.out.println(Thread.currentThread().getName() + " execute");
            }
        });
        thread.start();
        Thread.sleep(1000);
        stop = true;
        thread.join();
        System.out.println("finish");
        AtomicTest atomicTest = new AtomicTest();
        
    }

    public AtomicTest getThis() {
        return this;
    }

    private static class Singleton {

        static Singleton singleton;

        static Singleton getInstance() {
            if (singleton == null) {
                synchronized (Singleton.class) {
                    if (singleton == null) {
                        singleton = new Singleton();
                    }
                }
            }
            return singleton;
        }
    }
}

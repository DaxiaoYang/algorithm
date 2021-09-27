package com.concurrent;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/9/26
 */
public class ThreadTest {


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                if (Thread.interrupted()) {
                    break;
                }
                try {
                    System.out.println(Thread.currentThread().getName() + " run");
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().isInterrupted());
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " end");
        });
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}

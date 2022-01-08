package com.concurrent;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/8
 */
public class CountDownLatchDemo {

    private static Queue<Integer> resQueue1 = new ArrayDeque<>();

    private static Queue<Integer> resQueue2 = new ArrayDeque<>();



    public static void main(String[] args) throws InterruptedException {
//        threadMethod();
//        countDownLatchMethod();
        cyclicBarrierMethod();
    }

    private static void cyclicBarrierMethod() {
        Executor threadPool = Executors.newFixedThreadPool(1);
        CyclicBarrier barrier = new CyclicBarrier(2, () -> {
            threadPool.execute(() -> {
                Integer res1 = resQueue1.poll();
                Integer res2 = resQueue2.poll();
                System.out.println("process io task results:" + (res1 + res2));
            });
        });
        Thread t1 = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + " run over");
                    resQueue1.offer(1);
                    try {
                        barrier.await();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + " run over");
                    resQueue2.offer(2);
                    try {
                        barrier.await();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
    }

    private static void countDownLatchMethod() throws InterruptedException {
        Executor threadPool = Executors.newFixedThreadPool(2);
        while (true) {
            CountDownLatch latch = new CountDownLatch(2);
            threadPool.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " run over");
                latch.countDown();
            });
            threadPool.execute(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " run over");
                latch.countDown();
            });
            latch.await();
            System.out.println("process io task results");
        }
    }

    private static void threadMethod() throws InterruptedException {
        while (true) {
            // 是一个定时操作
            IoTask ioTask1 = new IoTask(1000);
            IoTask ioTask2 = new IoTask(3000);
            Thread t1 = new Thread(ioTask1);
            Thread t2 = new Thread(ioTask2);
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            System.out.println("process io task results");
        }
    }

    private static class IoTask implements Runnable {

        private long runningTime;

        public IoTask(long runningTime) {
            this.runningTime = runningTime;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(runningTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " run over");
        }
    }
}

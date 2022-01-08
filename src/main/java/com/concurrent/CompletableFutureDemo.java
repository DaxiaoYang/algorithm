package com.concurrent;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/12
 */
public class CompletableFutureDemo {

    public static void main(String[] args) {
//        aggregate();

//        chainedDemo();

//        orDemo();
    }

    private static void orDemo() {
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            int i = new Random().nextInt(5);
            try {
                TimeUnit.SECONDS.sleep(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return String.valueOf(i);
        });
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            int i = new Random().nextInt(5);
            try {
                TimeUnit.SECONDS.sleep(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return String.valueOf(i);
        });
        // OR关系
        CompletableFuture<String> f3 = f1.applyToEither(f2, s -> s);
        try {
            System.out.println(f3.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void chainedDemo() {
        CompletableFuture<String> fs = CompletableFuture
                .supplyAsync(() -> "Hello world")
                .thenApply(s -> s + "daxiao")
                .thenApply(String::toUpperCase)
                .exceptionally(Throwable::getMessage)
                .whenComplete((s, t) -> {

                });
        System.out.println("主线程继续执行");
        try {
            System.out.println(fs.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void aggregate() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "t1 洗水壶...");
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + "t1 烧开水...");
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "over1";
        }, executorService);

        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "t2 洗茶壶...");
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + "t2 洗茶杯...");
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + "t2 拿茶叶...");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "over2";
        }, executorService);

        // 表示 等待f1 f2执行完成  AND关系
        CompletableFuture<String> f3 = f1.thenCombine(f2, (s1, s2) -> {
            // f1 f2执行完后 执行该段代码
            System.out.println(s1);
            System.out.println(s2);
            System.out.println(Thread.currentThread().getName() + "泡茶");
            return "泡茶完成";
        });
        try {
            System.out.println(Thread.currentThread().getName() + f3.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

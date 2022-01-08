package com.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/12
 */
public class CompletionServiceDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        requestPriceAsync();

        dubboForking();
    }

    private static void dubboForking() throws InterruptedException, ExecutionException {
        // dubbo forking模式
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        ExecutorCompletionService<Integer> service = new ExecutorCompletionService<>(threadPool);
        List<Future<Integer>> futureList = new ArrayList<>();
        Future<Integer> f1 = service.submit(() -> {
            int timeout = new Random().nextInt(5);
            TimeUnit.SECONDS.sleep(timeout);
            return 1;
        });
        Future<Integer> f2 = service.submit(() -> {
            int timeout = new Random().nextInt(5);
            TimeUnit.SECONDS.sleep(timeout);
            return 2;
        });
        Future<Integer> f3 = service.submit(() -> {
            int timeout = new Random().nextInt(5);
            TimeUnit.SECONDS.sleep(timeout);
            return 3;
        });
        futureList.add(f1);
        futureList.add(f2);
        futureList.add(f3);
        try {
            Future<Integer> take = service.take();
            System.out.println(take.get());
        } finally {
            for (Future<Integer> integerFuture : futureList) {
                integerFuture.cancel(true);
            }
        }
    }

    private static void requestPriceAsync() throws InterruptedException, ExecutionException {
        // 异步询价
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        ExecutorCompletionService<Integer> service = new ExecutorCompletionService<>(threadPool);
        service.submit(() -> {
            int timeout = new Random().nextInt(5);
            TimeUnit.SECONDS.sleep(timeout);
            return 1;
        });
        service.submit(() -> {
            int timeout = new Random().nextInt(5);
            TimeUnit.SECONDS.sleep(timeout);
            return 2;
        });
        service.submit(() -> {
            int timeout = new Random().nextInt(5);
            TimeUnit.SECONDS.sleep(timeout);
            return 3;
        });
        for (int i = 0; i < 3; i++) {
            Future<Integer> take = service.take();
            System.out.println(take.get());
        }
    }
}

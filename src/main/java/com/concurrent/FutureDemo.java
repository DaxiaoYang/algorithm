package com.concurrent;

import java.util.concurrent.*;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/10
 */
public class FutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        FutureTask<String> f2 = new FutureTask<>(new Task2());
        FutureTask<String> f1 = new FutureTask<>(new Task1(f2));
        executorService.submit(f2);
        executorService.submit(f1);
        System.out.println(f1.get());
    }

    private static class Task1 implements Callable<String> {

        private FutureTask<String> task2Result;

        public Task1(FutureTask<String> task2Result) {
            this.task2Result = task2Result;
        }

        @Override
        public String call() throws Exception {
            System.out.println("洗水壶");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("烧开水");
            TimeUnit.SECONDS.sleep(10);
            String s = task2Result.get();
            System.out.println( s + " 泡茶");
            return "over";
        }
    }

    private static class Task2 implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println("洗茶壶");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("洗茶杯");
            TimeUnit.SECONDS.sleep(2);
            System.out.println("拿茶叶");
            TimeUnit.SECONDS.sleep(1);
            return "茶的准备结束";
        }
    }
}

package com.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/13
 */
public class ForkJoinPoolDemo {


    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(8);
        long start = System.currentTimeMillis();
        Integer invoke = pool.invoke(new Fibonacci(50));
//        int invoke = f(50);
        System.out.println(invoke);
        System.out.println(System.currentTimeMillis() - start);
    }

    private static int f(int n) {
        if (n <= 1) {
            return 1;
        }
        return f(n - 1) + f(n - 2);
    }

    private static class Fibonacci extends RecursiveTask<Integer> {

        private int n;

        public Fibonacci(int n) {
            this.n = n;
        }

        @Override
        protected Integer compute() {
            if (n <= 1) {
                return 1;
            }
            Fibonacci subTask1 = new Fibonacci(n - 1);
            subTask1.fork();
            Fibonacci subTask2 = new Fibonacci(n - 2);
            return subTask2.compute() + subTask1.join();
        }
    }
}

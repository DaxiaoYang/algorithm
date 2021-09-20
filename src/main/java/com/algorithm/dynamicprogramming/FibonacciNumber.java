package com.algorithm.dynamicprogramming;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/9/2
 */
public class FibonacciNumber {

    public int fib(int n) {
        // 边界条件
        if (n < 2) {
            return n;
        }
        // dp[i] 表示 f(i)的值 用额外的存储空间存储重复子问题的值
        int[] dp = new int[n + 1];
        // 初始化 dp[0] = 0 dp[1] = 1
        dp[1] = 1;
        // 递推公式: i = (i - 1) + (i - 2) 遍历顺序 ->
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public int fib2(int n) {
        if (n < 2) {
            return n;
        }
        // 只需维护三个变量即可
        //  a = f(n - 2) b = f(n - 1) c = f(n)
        int a = 0, b = 1, c = 0;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }
}

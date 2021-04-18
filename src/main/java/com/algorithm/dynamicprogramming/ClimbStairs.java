package com.algorithm.dynamicprogramming;

public class ClimbStairs {

    public static void main(String[] args) {
        System.out.println(climbStairs(8));
        System.out.println(climbStairsDp(8));
    }

    public static int climbStairs(int n) {
        // memo存储子问题计算出的结果 避免重复计算
        int[] memo = new int[n + 1];
        // base case
        memo[1] = 1;
        memo[0] = 1;
        return dfs(n, memo);
    }

    private static int dfs(int n, int[] memo) {
        // 如果子问题已经计算过 不重复计算 直接返回子结果
        if (memo[n] != 0) {
            return memo[n];
        }
        // 递推公式(最优子结构)： f(n) = f(n - 1) + f(n - 2)
        int res = dfs(n - 1, memo) + dfs(n - 2, memo);
        memo[n] = res;
        return res;
    }


    public static int climbStairsDp(int n) {
        int[] status = new int[n + 1];
        status[0] = 1;
        status[1] = 1;
        for (int i = 2; i <= n; i++) {
            status[i] = status[i - 1] + status[i - 2];
        }
        return status[n];
    }
}

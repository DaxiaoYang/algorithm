package com.algorithm.dynamicprogramming;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/9/3
 */
public class MinCostClimbingStairs {

    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;
        // 1.dp[i] 表示到达i所需花费的成本
        int[] dp = new int[len + 1];
        // 2.初始化: dp[0] = 0 dp[1] = 0 因为一开始就能到达0 1所以这两个不需要成本
        for (int i = 2; i <= len; i++) {
            // 3.递推公式: 当前的状态可由前面两个位置的状态推导而来
            // 花费cost最少的
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        // dp[len]表示到达顶层所需最少cost
        return dp[len];
    }
}

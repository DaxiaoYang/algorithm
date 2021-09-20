package com.algorithm.dynamicprogramming;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/9/16
 * L518 完全背包问题
 */
public class CoinChange2 {

    public int change(int amount, int[] coins) {
        // 1.dp[j] 装满容量为j的背包的组合数有多少种
        int[] dp = new int[amount + 1];
        // 3.dp[0] 容量为0的背包装满组合只有一个种
        dp[0] = 1;
        // 4.先遍历物品 再遍历容量 因为每个coins数量不限 同一个物品可以选多次 所以是完全背包
        // 所以 内层循环从左到右
        for (int coin : coins) {
            for (int j = coin; j <= amount; j++) {
                // 2.dp[j] += dp[j - coins[i]] j >= coins[i]
                dp[j] += dp[j - coin];
            }
        }
        return dp[amount];
    }
}

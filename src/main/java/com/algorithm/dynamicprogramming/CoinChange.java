package com.algorithm.dynamicprogramming;

public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        // status[i]表示组合成i所需的最少的coin的数量
        int[] status = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            // 最优子结构： (i) = 1 + min(i - xi) (i >= xi, xi belongs to coins)
            int preStatusMin = Integer.MAX_VALUE;
            for (int coin : coins) {
                // 如果当前状态的前一个状态是存在且合法（可以到达的）的
                if (i >= coin && status[i - coin] != -1) {
                    // 取前一个状态中的最小值
                    preStatusMin = Math.min(preStatusMin, status[i - coin]);
                }
            }
            // 不存在前一个状态：不可到达 存在前一个状态：前一个状态的值 + 1
            status[i] = preStatusMin == Integer.MAX_VALUE ? -1 : preStatusMin + 1;
        }
        return status[amount];
    }


    public int coinChangeTopDown(int[] coins, int amount) {
        return dfs(amount, new int[amount + 1], coins);
    }

    private int dfs(int amount, int[] memo, int[] coins) {
        // 边界 说明已组合完
        if (amount == 0) {
            return 0;
        }
        // 不计算重复子问题 给memo[i]赋值时就保证了赋值时候是最小的（定义决定）
        if (memo[amount] != 0) {
            return memo[amount];
        }
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            if (amount >= coin) {
                int prev = dfs(amount - coin, memo, coins);
                // memo[i] = -1表示状态不可达
                if (prev != -1) {
                    min = Math.min(min, prev);
                }
            }
        }
        memo[amount] = min == Integer.MAX_VALUE ? -1 : min + 1;
        return memo[amount];
    }
}

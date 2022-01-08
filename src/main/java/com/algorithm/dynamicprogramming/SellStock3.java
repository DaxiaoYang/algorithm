package com.algorithm.dynamicprogramming;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/4
 */
public class SellStock3 {

    public int maxProfit(int[] prices) {
        // 可以优化空间 只需要5个空间存储状态
        int len = prices.length;
        // 1.dp[i][j] 表示第i天j种状态下 所能获得的最大金额
        // 0 未操作 1 第一次买入 2 第一次卖出 3 第二次买入 4 第二次卖出
        int[][] dp = new int[len][5];
        // 3. 买入初始化
        dp[0][1] = -prices[0];
        dp[0][3] = -prices[0];
        // 4. ->
        for (int i = 1; i < len; i++) {
            // 2. 对每种状态都有两个方向：操作 或者 不操作 且第j种状态依赖[j] 和 [j - 1]种状态
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
            dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
        }
        return dp[len - 1][4];
    }
}

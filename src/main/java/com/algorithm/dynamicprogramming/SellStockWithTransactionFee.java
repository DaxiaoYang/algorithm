package com.algorithm.dynamicprogramming;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/9
 */
public class SellStockWithTransactionFee {

    public int maxProfit(int[] prices, int fee) {
        int len = prices.length;
        // 1.dp[i][j] 表示第i天 第j种状态所能获取的最大金额  0:持有股票的状态 1：不持有股票的状态
        int[][] dp = new int[len][2];
        // 3.
        dp[0][0] = -prices[0];
        // 4. ->
        for (int i = 1; i < len; i++) {
            // 2. 无操作 或者 买入 卖出
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i] - fee);
        }
        return dp[len - 1][1];
    }
}

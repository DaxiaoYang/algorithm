package com.algorithm.dynamicprogramming;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/8
 */
public class SellStockWithCoolDown {

    public static void main(String[] args) {
        int[] prices = {7,4,2,1};
        System.out.println(maxProfit(prices));
    }

    public static int maxProfit(int[] prices) {
        // 1.dp[i][j] 第i天第j种状态下 金额的最大值
        int len = prices.length;
        /*
         * 持有 0  不持有 1 2 3
         * 0 买入股票的状态：当天买 或者之前买了今天不操作依然持有
         * 1 当天卖
         * 2 冷冻期
         * 3 已过冷冻期 但无操作 依然卖出
         */
        int[][] dp = new int[len][4];
        dp[0][0] = -prices[0];
        for (int i = 1; i < len; i++) {
            // 不操作 或者当天买入 买入只能是昨天是冷冻期或者已过冷冻期
            dp[i][0] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][2], dp[i - 1][3]) - prices[i]);
            // 当天卖 昨天必须是买入股票
            dp[i][1] = dp[i - 1][0] + prices[i];
            // 冷冻期 昨天是刚卖出
            dp[i][2] = dp[i - 1][1];
            // 昨天是冷冻期或者前几天是冷冻期
            dp[i][3] = Math.max(dp[i - 1][2], dp[i - 1][3]);
        }
        return Math.max(dp[len - 1][1], Math.max(dp[len - 1][2], dp[len - 1][3]));
    }
}

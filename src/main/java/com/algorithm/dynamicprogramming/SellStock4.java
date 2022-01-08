package com.algorithm.dynamicprogramming;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/5
 */
public class SellStock4 {


    public int maxProfit(int k, int[] prices) {
        if (k == 0 || prices.length == 0) {
            return 0;
        }
        int len = prices.length;
        int[][] dp = new int[len][2 * k + 1];
        for (int i = 1; i <= 2 * k; i++) {
            if (i % 2 == 1) {
                dp[0][i] = -prices[0];
            }
        }
        for (int i = 1; i < len; i++) {
            for (int j = 1; j <= 2 * k; j++) {
                if (j % 2 == 1) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] - prices[i]);
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] + prices[i]);
                }
            }
        }
        return dp[len - 1][2 * k];
    }

    public int maxProfit2(int k, int[] prices) {
        if (k == 0 || prices.length == 0) {
            return 0;
        }
        int len = prices.length;
        int[] dp = new int[2 * k + 1];
        for (int i = 1; i <= 2 * k; i++) {
            if (i % 2 == 1) {
                dp[i] = -prices[0];
            }
        }
        for (int i = 1; i < len; i++) {
            for (int j = 1; j <= 2 * k; j++) {
                if (j % 2 == 1) {
                    dp[j] = Math.max(dp[j], dp[j - 1] - prices[i]);
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1] + prices[i]);
                }
            }
        }
        return dp[2 * k];
    }
}

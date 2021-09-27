package com.algorithm.dynamicprogramming;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/9/27
 */
public class SellStock {

    public int maxProfit(int[] prices) {
        int len = prices.length;
        // 1. dp[i][0]表示第i天持有股票(之前已经买了 或者 当天买的)所能获取的最大金额
        //    dp[i][1]表示第i天不持有股票(之前已经就没买或者已经卖了 或者 当天卖的)所能获取的最大金额
        int[][] dp = new int[len][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < len; i++) {
            // 2.
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
            // 继承状态 或 当天卖出
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }
        return dp[len - 1][1];
    }


    public int maxProfit2(int[] prices) {
        int len = prices.length;
        // 优化 因为只会用到4个状态 当前天 和 前一天 持有和不持有
        int[][] dp = new int[2][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < len; i++) {
            dp[i % 2][0] = Math.max(dp[(i - 1) % 2][0], -prices[i]);
            dp[i % 2][1] = Math.max(dp[(i - 1) % 2][1], dp[(i - 1) % 2][0] + prices[i]);
        }
        return dp[(len - 1) % 2][1];
    }

    public int maxProfit3(int[] prices) {
        int currMin = Integer.MAX_VALUE;
        int max = 0;
        for (int price : prices) {
            // 贪心：取最左最小值 取最右最大值
            currMin = Math.min(currMin, price);
            max = Math.max(max, price - currMin);
        }
        return max;
    }
}

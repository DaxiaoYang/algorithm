package com.algorithm.dynamicprogramming;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/9/28
 */
public class SellStock2 {

    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        System.out.println(maxProfit(prices));
    }

    public static int maxProfit(int[] prices) {
        int len = prices.length;
        if (len < 1) {
            return 0;
        }
        // 1.dp[i][0] 第i天持有股票带来的最大收益 dp[i][1] 不持有股票带来的最大收益
        int[][] dp = new int[len][2];
        // 3.
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        // 4 ->
        for (int i = 1; i < len; i++) {
            // 2. 两个动作：什么都不做（继承状态） 买 所以状态可由两个方向推过来
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            // 两个动作：什么都不做（继承状态） 卖 所以状态可由两个方向推过来
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }
        return dp[len - 1][1];
    }


    public int maxProfit2(int[] prices) {
        // 滚动数组
        int len = prices.length;
        if (len < 1) {
            return 0;
        }
        int[][] dp = new int[2][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < len; i++) {
            dp[i % 2][0] = Math.max(dp[(i - 1) % 2][0], dp[(i - 1) % 2][1] - prices[i]);
            dp[i % 2][1] = Math.max(dp[(i - 1) % 2][1], dp[(i - 1) % 2][0] + prices[i]);
        }
        return dp[(len - 1) % 2][1];
    }

    public int maxProfit3(int[] prices) {
        // 贪心：局部最优：找出一段递增区间的高度差 全局最优：找出所有递增区间的高度差
        return -1;
    }
}

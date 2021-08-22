package com.algorithm.greedy;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/8/15
 */
public class BestTimeToBuyAndSellStock {

    /**
     * 分解：把利润的获取分解到每天的维度 prices[2] - prices[0] = prices[2] - [1] + ([1] - [0])
     * 贪心：
     * 局部：每天只获取正利润
     * 全局：获取最大利润
     */
    public int maxProfit(int[] prices) {
        int sum = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i] < prices[i + 1]) {
                sum += prices[i + 1] - prices[i];
            }
        }
        return sum;
    }

}

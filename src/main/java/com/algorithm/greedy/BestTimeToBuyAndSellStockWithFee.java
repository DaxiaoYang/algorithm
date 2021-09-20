package com.algorithm.greedy;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/8/31
 */
public class BestTimeToBuyAndSellStockWithFee {

    public int maxProfit(int[] prices, int fee) {
        int buy = Integer.MAX_VALUE;
        int sum = 0;
        for (int price : prices) {
            // 更新 价格 + 手续费的最小值（模拟买入）
            if (price + fee < buy) {
                buy = price + fee;
            } else if (price > buy) {
                // 如果有利可图 则卖出累积收益 因为可能不是连续上升区间的最后一个
                sum += price - buy;
                // 这一步是为了在连续上升的区间中不做连续的交易（只交一次手续费） 而是只累积收益 模拟最后一次才卖出
                buy = price;
            }
        }
        return sum;
    }
}

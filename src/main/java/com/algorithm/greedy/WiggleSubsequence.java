package com.algorithm.greedy;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/8/13
 */
public class WiggleSubsequence {

    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        // 将单调区间的中间结点全部去掉 只留下单调区间的左右结点
        // 局部最优：每个单调区间都只保留两端 -> 全局最优:整个序列都是上下波动的
        // 默认最右边的值为峰值
        int len = 1;
        // 前一个峰值与下一个元素的差值
        int prevDiff = 0;
        // 当前元素与下一个元素的差值
        int currDiff = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            currDiff = nums[i + 1] - nums[i];
            // <=0 和 >=0 是为了考虑只有两个元素的情况
            if (prevDiff <= 0 && currDiff > 0 || prevDiff >= 0 && currDiff < 0) {
                len++;
                prevDiff = currDiff;
            }
        }
        return len;
    }
}

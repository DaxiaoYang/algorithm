package com.algorithm.dynamicprogramming;

import java.util.Arrays;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/10
 */
public class LongestIncreasingSubsequence {

    public int lengthOfLIS(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        int res = 0;
        int len = nums.length;
        // 1.dp[i] 考虑元素范围为[0,i]且以i为结尾的最长子序列的长度
        int[] dp = new int[len];
        // 3. 初始化
        Arrays.fill(dp, 1);
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                // 2.考虑之前的所有可以与当前元素构成递增的子序列
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            // 因为最长子序列长度不一定以最后一个元素结尾 所以取最大值
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}

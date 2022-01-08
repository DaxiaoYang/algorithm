package com.algorithm.dynamicprogramming;

import java.util.Arrays;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/11
 */
public class LongestContinuousIncreasingSubsequence {


    public int findLengthOfLCIS(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int len = nums.length;
        // 1.dp[i] 为考虑范围为[0,i] 且以i结尾的最长连续递增子序列的长度
        int[] dp = new int[len];
        // 3.
        Arrays.fill(dp, 1);
        int res = 0;
        // 4. ->
        for (int i = 1; i < len; i++) {
            // 2. 连续子序列 状态只取决于前面一个的状态 而不连续子序列 状态取决于前面的0-i-1 状态
            if (nums[i] > nums[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}

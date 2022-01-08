package com.algorithm.dynamicprogramming;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/11
 */
public class MaximumLengthofRepeatedSubarray {

    public int findLength(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        // 1.dp[i][j] 表示 nums1 [0,i-1] nums2 [0,j-1] 这两个范围内的最长公共子数组的长度
        // 3. dp[0][j] dp[i][0] 都没有意义 只是为推导公式
        int[][] dp = new int[len1 + 1][len2 + 1];
        int res = 0;
        // 4. ↓ →
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                // 2. dp[i][j] 的状态只能由dp[i - 1][j - 1]的状态转变而来
                if (nums1[i - 1] == nums2[j - 1]) {
                    // 当前指针指向的元素相同 则长度为之前的状态 + 1
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res;
    }
}

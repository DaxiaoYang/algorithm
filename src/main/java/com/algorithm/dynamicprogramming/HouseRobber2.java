package com.algorithm.dynamicprogramming;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/9/24
 */
public class HouseRobber2 {

    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        System.out.println(rob(nums));
    }

    public static int rob(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return nums[0];
        }
        // 分为两种情况：1.考虑第一位不考虑最后一位 2.考虑最后一位不考虑第一位
        return Math.max(rob(nums, 0, len - 2), rob(nums, 1, len - 1));
    }

    private static int rob(int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        }
        int[] dp = new int[end - start + 1];
        dp[0] = nums[start];
        dp[1] = Math.max(nums[start], nums[start + 1]);
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[start + i], dp[i - 1]);
        }
        return dp[dp.length - 1];
    }
}

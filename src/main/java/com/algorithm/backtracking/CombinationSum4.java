package com.algorithm.backtracking;

import java.util.Arrays;

public class CombinationSum4 {

    private static int[] memo;

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(combinationSum4(nums, 4));
    }

    public static int combinationSum4(int[] nums, int target) {
        memo = new int[target + 1];
        Arrays.fill(memo, -1);
        // base case
        memo[0] = 1;
        return dfs(nums, target);
    }

    // 回溯 会产生计算重复的子问题的情况 如计算comb(4) = comb(4 - 1) + comb(4 - 2) + comb(4 - 3)
    private static int dfsWithoutMemo(int[] nums, int target) {
        if (target == 0) {
            return 1;
        }
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (target >= nums[i]) {
                // 递：将问题分解为性质相同 但规模较小的子问题
                // 归：将子问题返回的结果合并为问题的解
                count += dfs(nums, target - nums[i]);
            }
        }
        return count;
    }

    // backtrack + memo 存储子问题计算结果 不重复计算
    private static int dfs(int[] nums, int target) {
        if (memo[target] != -1) {
            return memo[target];
        }
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (target >= nums[i]) {
                count += dfs(nums, target - nums[i]);
            }
        }
        memo[target] = count;
        return count;
    }

    // dp 最优子结构：
    // comb[target] = sum(comb[target - nums[i]]), where 0 <= i < nums.length, and target >= nums[i]
    public static int combinationSum4Dp(int[] nums, int target) {
        // count[i] 表示和为i的组合个数
        int[] count = new int[target + 1];
        // base case
        count[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i >= nums[j]) {
                    count[i] += count[i - nums[j]];
                }
            }
        }
        return count[target];
    }
}

package com.algorithm.dynamicprogramming;

public class BurstBallons {

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(maxCoins(nums));
    }

    public static int maxCoins(int[] nums) {
        // 预留两个位置给nums[-1] nums[len]
        int[] extendedNums = new int[nums.length + 2];
        int n = 1;
        // 因为选择0不会带来收益 所以先爆掉
        for (int num : nums) {
            if (num > 0) {
                extendedNums[n++] = num;
            }
        }
        // 边界
        extendedNums[0] = 1;
        extendedNums[n++] = 1;
        // memo[i][j]表示 [i + 1,j - 1]这个范围内所能获取的最大收益
        int[][] memo = new int[n][n];
        return dfs(memo, extendedNums, 0, n - 1);
    }

    /**
     * 不是从开始到结束 而是逆向思维 先选择最后一个数 因为选择最后一个数 从后往前推 边界是确定的
     * @return [left + 1,right - 1]在这个范围内所能获取的最大收益
     */
    private static int dfs(int[][] memo, int[] nums, int left, int right) {
        // 所选范围内没有数
        if (left + 1 == right) {
            return 0;
        }
        // 不重复计算子问题
        if (memo[left][right] != 0) {
            return memo[left][right];
        }
        int res = 0;
        // 倒数第x个数选择的是i 最后这个阶段的积 + 上一个阶段的积
        for (int i = left + 1; i < right; i++) {
            res = Math.max(res, nums[left] * nums[i] * nums[right] + dfs(memo, nums, left, i) + dfs(memo, nums, i, right));
        }
        memo[left][right] = res;
        return res;
    }
}

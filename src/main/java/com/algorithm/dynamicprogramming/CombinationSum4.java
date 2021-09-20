package com.algorithm.dynamicprogramming;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/9/18
 */
public class CombinationSum4 {

    public int combinationSum4(int[] nums, int target) {
        // 1.dp[i] 和为数i的排列数有多少个
        int[] dp = new int[target + 1];
        // 3. 无意义的初始化
        dp[0] = 1;
        // 4. 遍历背包容量 再遍历物品(内层正序因为是完全背包 逆序则是0-1背包)
        // 因为先遍历物品 再遍历背包得到是组合
        // 计算dp[4]的时候，结果集只有 {1,3} 这样的集合，不会有{3,1}这样的集合，因为nums遍历放在外层，3只能出现在1后面！
        for (int i = 0; i <= target; i++) {
            for (int num : nums) {
                if (num > i) {
                    continue;
                }
                // 2. dp[i] += dp[i - nums[j]]
                dp[i] += dp[i - num];
            }
        }
        return dp[target];
    }
}

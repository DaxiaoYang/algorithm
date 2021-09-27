package com.algorithm.dynamicprogramming;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/9/24
 */
public class HouseRobber {

    public int rob(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return nums[0];
        }
        // 1.dp[i] 表示考虑的范围为[0, i] 可打劫到的最大的金额数
        int[] dp = new int[len];
        // 3. 初始化
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        // 后依赖于前 所以正序遍历
        for (int i = 2; i < len; i++) {
            // 2. dp[i]来源 对每家 都有两种选择: 抢 / 不抢  抢则只能继承dp[i - 2] 不抢则能继承dp[i - 1]
            // 因为不能连续抢
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[len - 1];
    }
}

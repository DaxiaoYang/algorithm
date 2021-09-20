package com.algorithm.dynamicprogramming;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/9/14
 * L1049
 * 问题转化为求一个集合 需要拆分为两个大小相差最小的子集
 */
public class LastStoneWeight2 {

    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        // 一半
        int target = sum / 2;
        // 1.dp[j]表示容量j的背包 最多能装dp[j]重量的石头
        int[] dp = new int[target + 1];
        // 3.dp[0] = 0
        // 4. 先遍历物品 内层遍历中逆序遍历背包容量
        for (int stone : stones) {
            for (int j = target; j >= stone; j--) {
                // 2.转化为0-1背包问题 重量为stone[i] 价值也为stone[i]
                // dp[j] = max(dp[j], dp[j - stone[i]] + stone[i]) 对每个stone[i]都有不选和选两种选择
                dp[j] = Math.max(dp[j], dp[j - stone] + stone);
            }
            // 剪枝
            if (dp[target] == target) {
                break;
            }
        }
        // 因为sum / 2为向下取整 所以 target <= 1/2 * sum 所以左边的(sum - dp[target]) >= 右边的dp[target]
        return (sum - dp[target]) - dp[target];
    }
}

package com.algorithm.dynamicprogramming;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/9/13
 * L416 问题转化为求容量为数组总和的1/2的背包是否能装满
 * 套0-1背包问题：
 * 1.背包体积为1/2 sum
 * 2.背包中的元素不可重复放入 (0-1 不放-放)
 * 3.数组的中数即为元素
 * 4.元素的重量和价值都是nums[i]
 * 5.如果最大容量的背包能装满 说明存在一个子集为集合总和的1/2
 */
public class PartitionEqualSubsetSum {

    public boolean canPartition(int[] nums) {
        if (nums.length < 2) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 总和为奇数 不可分为两个
        if (sum % 2 == 1) {
            return false;
        }
        int target = sum / 2;
        // 1.dp[j] 表示容量为j的背包 最多能装下多少数值的元素和 dp[j] <= j 背包的装的体积 <= 背包的容量
        int[] dp = new int[target + 1];
        // 3.dp[0] = 0
        // 4. 先遍历物品 内层中再逆序遍历容量
        for (int i = 0; i < nums.length; i++) {
            for (int j = target; j >= nums[i]; j--) {
                // 2.dp[j] = max(dp[j], dp[j - nums[i]] + nums[i]) 不选/选当前元素 注意重量和价值都是nums[i]
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
            // 剪枝：如果已经找到一个子集了满足需求 就不继续进行计算 因为dp[j]的值的变化是非递减的 且最大为j
            if (dp[target] == target) {
                break;
            }
        }
        // 容量为sum / 2的背包能被装满 说明存在总和为sum / 2的子集 
        return dp[target] == target;
    }
}

package com.algorithm.dynamicprogramming;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/9/15
 * L494
 * 问题转化：化零为整 将选取零散的个数 -> 选取一个子集
 * left(left子集里面的数的符号都是+) - right(即right子集里面的数的符号都是-) = target
 * left + right = sum
 * => left = (target + sum) / 2
 * 此时问题转化为使得其总和为left的组合有多少种
 */
public class TargetSum {


    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 为奇数(不能使得left - right = target) 或者 目标数绝对值（因为可以取正负符号） > sum
        if ((sum + target) % 2 == 1 || Math.abs(target) > sum) {
            return 0;
        }
        int capacity = (sum + target) / 2;
        // 1.dp[j] 填满容量为j的背包有多少种方法
        int[] dp = new int[capacity + 1];
        // 3.dp[0] = 1表示容量为0的背包有一种方法
        dp[0] = 1;
        // 4.遍历物品 就是一个不断扩大 可选区域的过程 原始的二维数组dp[i][j]表示 可选的物品为[0-i] 填满容量为j的背包的方式有多少种
        for (int num : nums) {
            for (int j = capacity; j >= num; j--) {
                // 2.dp[j] += dp[j - nums[i]]
                /*
                 * 举一个例子,nums[i] = 2： dp[3]，填满背包容量为3的话，有dp[3]种方法。
                 * 那么只需要搞到一个2（nums[i]），有dp[3]方法可以凑齐容量为3的背包，相应的就有多少种方法可以凑齐容量为5的背包。
                 * 那么需要把 这些方法累加起来就可以了，dp[j] += dp[j - nums[i]]
                 */
                dp[j] += dp[j - num];
            }
        }
        return dp[capacity];
    }
}

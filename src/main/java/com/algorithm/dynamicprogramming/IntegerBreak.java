package com.algorithm.dynamicprogramming;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/9/6
 */
public class IntegerBreak {

    public int integerBreak(int n) {
        // 1. dp[i]表示值为i的数 所能拆成乘积的最大的值
        int[] dp = new int[n + 1];
        // 3.初始化
        dp[2] = 1;
        // 2. 递归公式 对于dp[i] 有两个来源 j=[1, i - 2] j == i - 1时 i - j == 1 而dp[1] 没有定义
        // 一个是将该数拆成两个数 (i - j) * j
        // 一个是将该数拆成两个以上的数 dp[i - j] * j
        // 取其中的最大值
        // 4.遍历顺序：-> 因为dp[i]的状态依赖于dp[i - j]的状态
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j < i - 1; j++) {
                dp[i] = Math.max(dp[i], Math.max((i - j) * j, dp[i - j] * j));
            }
        }
        return dp[n];
    }
}

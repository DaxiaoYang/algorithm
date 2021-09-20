package com.algorithm.dynamicprogramming;

public class ClimbStairs {

    public static void main(String[] args) {
        System.out.println(climbStairs(8));
        System.out.println(climbStairsDp(8));
    }

    public static int climbStairs(int n) {
        // memo存储子问题计算出的结果 避免重复计算
        int[] memo = new int[n + 1];
        // base case
        memo[1] = 1;
        memo[0] = 1;
        return dfs(n, memo);
    }

    private static int dfs(int n, int[] memo) {
        // 如果子问题已经计算过 不重复计算 直接返回子结果
        if (memo[n] != 0) {
            return memo[n];
        }
        // 递推公式(最优子结构)： f(n) = f(n - 1) + f(n - 2)
        int res = dfs(n - 1, memo) + dfs(n - 2, memo);
        memo[n] = res;
        return res;
    }


    public static int climbStairsDp(int n) {
        int[] status = new int[n + 1];
        status[0] = 1;
        status[1] = 1;
        for (int i = 2; i <= n; i++) {
            status[i] = status[i - 1] + status[i - 2];
        }
        return status[n];
    }

    public int climbStairs2(int n) {
        if (n < 3) {
            return n;
        }
        // dp[i]表示到达i层可用的方法数
        int[] dp = new int[n + 1];
        // 初始化
        dp[1] = 1;
        dp[2] = 2;
        // 递推公式
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 一共有关 1 2 3 4 .. m 个步伐可以选 问到达n层可以有的方法有多少种
     * 完全背包问题
     * @param n 背包容量
     * @param steps 物品(可以重复选)
     */
    public int climbStairsVariant(int n, int[] steps) {
        // 1.dp[i] 到达i层 可以用的方法有多少种（排列）
        int[] dp = new int[n + 1];
        // 3. dp[0] = 1 其他的都从这里推 没有什么特别的意思
        dp[0] = 1;
        // 4. 排列问题 先遍历容量 再遍历物品(正序 因为可以选多个)
        for (int i = 2; i <= n; i++) {
            for (int step : steps) {
                if (step > i) {
                    continue;
                }
                // 2. dp[i] += dp[i - nums[j]]
                dp[i] += dp[i - step];
            }
        }
        return dp[n];
    }
}

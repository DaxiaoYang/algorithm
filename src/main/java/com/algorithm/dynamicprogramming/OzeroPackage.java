package com.algorithm.dynamicprogramming;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/9/11
 * 0-1背包问题
 */
public class OzeroPackage {

    public static void main(String[] args) {
        int[] weights = {1,3,4};
        int[] values = {15,20,30};
        System.out.println(maxValue2D(4, weights, values));
        System.out.println(maxValue1D(4, weights, values));
    }


    public static int maxValue2D(int W, int[] weights, int[] values) {
        int n = weights.length;
        // 1. dp[i][j]表示在[0,i]个范围内进行选择 背包容量为j时 所能获取的最大价值
        int[][] dp = new int[n][W + 1];
        // 3. 初始化 dp[0][j] 当容量足够第一个放入时 价值为第一个 dp[i][0]背包容量为0 所以都是0
        for (int j = weights[0]; j <= W; j++) {
            dp[0][j] = values[0];
        }
        // 2. dp[i][j]的值值可能由两个方向过来 dp[i - 1][j] 就是不选择当前物品
        // dp[i - 1][j - weights[i]] + values[i] 选择当前物品 (j - weights[i] >= 0)
        // 先遍历物品 表示选不选择当前物品
        for (int i = 1; i < n; i++) {
            // 再遍历重量
            for (int j = 1; j <= W; j++) {
                // 当前背包重量小于当前待选择的物品 没办法选择物品
                if (j < weights[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 有两个方向可选时 取最大值
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i]] + values[i]);
                }
            }
        }
        return dp[n - 1][W];
    }

    public static int maxValue1D(int W, int[] weights, int[] values) {
        int n = weights.length;
        // 1.dp[j] 表示容量为j的背包的所能获取的最大价值 dp一维数组表示 上次选择物品后的状态
        int[] dp = new int[W + 1];
        // 3. 初始化 dp[0] = 0 其余也为0
        for (int i = 0; i < n; i++) {
            // 4. 先遍历物品 再遍历背包容量 且背包容量需要倒序遍历 因为正序遍历会污染上一次的状态 导致本轮状态不是由上一次状态得来
            for (int j = W; j >= weights[i] ; j--) {
                // 2. 从二维数组的递推状态得来 一个是不选择当前物品 继承上次的状态 一个是选择当前物品 获取价值
                dp[j] = Math.max(dp[j], dp[j - weights[i]] + values[i]);
            }
        }
        return dp[W];
    }
}

package com.algorithmlesson.dynamicprogramming;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2022/1/28
 */
public class Baggage {

    public static void main(String[] args) {
        int[] weights = {1,3,4};
        int[] values = {15,20,30};
        System.out.println(getMaxValue(weights, values, 4));
        System.out.println(getMaxValueOneDimension(weights, values, 4));
    }

    private static int getMaxValueOneDimension(int[] weights, int[] values, int limit) {
        int n = weights.length;
        // dp[j] 表示容量为j的背包可以装的最大价值为j
        int[] dp = new int[limit + 1];
        for (int i = 0; i < n; i++) {
            for (int j = limit; j >= weights[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weights[i]] + values[i]);
            }
        }
        return dp[limit];
    }

    private static int getMaxValue(int[] weights, int[] values, int limit) {
        int n = weights.length;
        // 1.dp[i][j] 为[0,i]物品中选择 放入到容量为j的背包中 的最大价值
        int[][] dp = new int[n][limit + 1];
        // 2.递推公式：dp[i][j] = max(dp[i - 1][j], v[i] + dp[i - 1][j - w[i]])
        // 3.初始化: 因为i都是从 i - 1推来的 所以i = 0需要初始化 j = 0背包装不了东西
        for (int i = 0; i < n; i++) {
            dp[i][0] = 0;
        }
        for (int j = weights[0]; j <= limit; j++) {
            dp[0][j] = values[0];
        }
        // 4.遍历顺序 先物品 再容量
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= limit; j++) {
                if (weights[i] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 不选 选
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i]] + values[i]);
                }
            }
        }
        return dp[n - 1][limit];
    }
}

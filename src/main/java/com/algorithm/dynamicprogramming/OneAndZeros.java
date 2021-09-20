package com.algorithm.dynamicprogramming;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/9/17
 */
public class OneAndZeros {

    public int findMaxForm(String[] strs, int m, int n) {
        // 1.dp[i][j] 当最多有i个0和j个1时 子集的最大大小
        int[][] dp = new int[m + 1][n + 1];
        int zeroNum;
        int oneNum;
        // 4. 先遍历物品：字符串 内层循环倒序遍历
        for (String str : strs) {
            zeroNum = 0;
            oneNum = 0;
            for (char c : str.toCharArray()) {
                if (c == '0') {
                    zeroNum++;
                } else {
                    oneNum++;
                }
            }
            // 还是0-1背包问题: 物品:字符串 重量：0个数和1个数（两个维度） 价值: 1（个数）
            // 2.dp[i][j] = max(dp[i][j], dp[i - zeroNum][j - oneNum] + 1)
            for (int i = m; i >= zeroNum; i--) {
                for (int j = n; j >= oneNum; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeroNum][j - oneNum] + 1);
                }
            }
        }
        return dp[m][n];
    }
}

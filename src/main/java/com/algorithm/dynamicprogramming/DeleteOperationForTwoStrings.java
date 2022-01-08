package com.algorithm.dynamicprogramming;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/15
 */
public class DeleteOperationForTwoStrings {

    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        // 1.dp[i][j]以A[i-1]结尾的子串 到 B[j-1]结尾的子串的编辑距离（只需允许删除）
        int[][] dp = new int[len1 + 1][len2 + 2];
        // 3. 使A[0,i-1]变成空串需要多少次删除操作
        for (int i = 1; i <= len1; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= len2; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                // 2.
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // 相同则不需要编辑距离
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 三个方向： 删A的一个 删B的一个 两个都删
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + 2);
                }
            }
        }
        return dp[len1][len2];
    }
}

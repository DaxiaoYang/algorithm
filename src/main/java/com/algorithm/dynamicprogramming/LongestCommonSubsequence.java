package com.algorithm.dynamicprogramming;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/12
 */
public class LongestCommonSubsequence {

    public int longestCommonSubsequence(String text1, String text2) {
        char[] c1 = text1.toCharArray();
        char[] c2 = text2.toCharArray();
        // 1.dp[i][j] text1[0,i-1] text2[0,j-1] 这两个子串的最长公共子序列
        int[][] dp = new int[c1.length + 1][c2.length + 1];
        for (int i = 1; i <= c1.length; i++) {
            for (int j = 1; j <= c2.length; j++) {
                // 就两种情况 指针当前指到的字符是否相等
                if (c1[i - 1] == c2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[c1.length][c2.length];
    }
}

package com.algorithm.dynamicprogramming;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/18
 */
public class LongestPalindromicSubsequence {


    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        // 1.dp[i][j] [i,j]这个范围内的最长回文子序列的长度
        int[][] dp = new int[len][len];
        // 3.递推公式没法计算出 i == j的情况 所以需要初始化
        for (int i = 0; i < len; i++) {
            dp[i][i] = 1;
        }
        // 4. [i][j] 依赖于[i+1][j-1] 所以↑ →
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                // 2.两种情况 s[i] == s[j] 2 +
                if (s.charAt(i) == s.charAt(j)) {
                    // abca
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                } else {
                    // 不等试着加入s[i] 或者加入s[j]
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][len - 1];
    }
}

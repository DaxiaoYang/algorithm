package com.algorithm.dynamicprogramming;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/13
 */
public class IsSubsequence {

    public boolean isSubsequence(String s, String t) {
        // 如果s与t的 最长公共子序列的长度为s的长度 则说明s为t的子序列
        int sLen = s.length();
        int tLen = t.length();
        if (sLen == 0) {
            return true;
        }
        if (tLen == 0) {
            return false;
        }
        char[] sChar = s.toCharArray();
        char[] tChar = t.toCharArray();
        int[][] dp = new int[sLen + 1][tLen + 1];
        for (int i = 1; i <= sLen; i++) {
            for (int j = 1; j <= tLen; j++) {
                if (sChar[i - 1] == tChar[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[sLen][tLen] == sLen;
    }
}

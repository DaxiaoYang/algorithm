package com.algorithm.dynamicprogramming;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/15
 */
public class DistinctSubsequences {

    public int numDistinct(String s, String t) {
        char[] sChar = s.toCharArray();
        char[] tChar = t.toCharArray();
        // 1.dp[i][j] 以s[i-1]结尾的子序列中 包含 t[0,j-1]的数量
        int[][] dp = new int[sChar.length + 1][tChar.length + 1];
        // 3.dp[i][0] 以s[i]结尾的子序列中 包含空序列的数量（都是1 s删完元素就是了）
        // dp[0][j] 空序列中 没有子序列 所以dp[0][j] = 0
        for (int i = 0; i < sChar.length; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= sChar.length; i++) {
            for (int j = 1; j <= tChar.length; j++) {
                // 2. bagg bag
                if (sChar[i - 1] == tChar[j - 1]) {
                    // 用s[i-1]匹配 与 不用s[i-1]匹配
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    // 不用s[i-1]匹配
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[sChar.length][tChar.length];
    }
}

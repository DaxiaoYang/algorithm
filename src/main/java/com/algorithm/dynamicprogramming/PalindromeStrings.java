package com.algorithm.dynamicprogramming;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/17
 */
public class PalindromeStrings {

    public int countSubstrings(String s) {
        int len = s.length();
        int count = 0;
        // 1.dp[i][j] 表示[i,j]这个子串是否是回文串
        boolean[][] dp = new boolean[len][len];
        // 4. 因为[i][j]依赖于[i+1][j-1] 状态是这样↗ 推的 所以 需要从下往上 从左往右遍历
        for (int i = len - 1; i >= 0; i--) {
            // 根据定义 j >= i
            for (int j = i; j < len; j++) {
                /*
                    2.两种情况:
                    s[i] != s[j] 那直接不用考虑
                    s[i] == s[j] 如果 子串长度为 1 或者 2 如 a aa 则是
                    是否需要进一步判断内部的子串是否是 如 cabac
                */
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i <= 1) {
                        dp[i][j] = true;
                        count++;
                    } else if (dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                        count++;
                    }
                }
            }
        }
        return count;
    }
}

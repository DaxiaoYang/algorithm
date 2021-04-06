package com.algorithm.dynamicprogramming;

public class LongestSubsequence {

    public static void main(String[] args) {
        char[] a = "mitcmu".toCharArray();
        char[] b = "mtacnu".toCharArray();
        System.out.println(getLongestSubsequenceLength(a, b));
    }

    public static int getLongestSubsequenceLength(char[] a, char[] b) {
        // 初始化状态表
        int m = a.length;
        int n = b.length;
        // states[i][j] 表示a[0,i] b[0,j]的最大子序列长度
        int[][] states = new int[m][n];
        if (a[0] == b[0]) {
            states[0][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            states[i][0] = states[i - 1][0];
        }
        for (int j = 1; j < n; j++) {
            states[0][j] = states[0][j - 1];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (a[i] == b[j]) {
                    states[i][j] = max(states[i - 1][j], states[i][j - 1], states[i - 1][j - 1] + 1);
                } else {
                    states[i][j] = max(states[i - 1][j], states[i][j - 1], states[i - 1][j - 1]);
                }
            }
        }
        return states[m - 1][n - 1];
    }

    private static int max(int a, int b, int c) {
        int max = a < b ? b : a;
        if (c > max) {
            max = c;
        }
        return max;
    }
}

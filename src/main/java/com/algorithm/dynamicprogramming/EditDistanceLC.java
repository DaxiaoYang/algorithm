package com.algorithm.dynamicprogramming;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/5/8
 */
public class EditDistanceLC {

    public static void main(String[] args) {
        System.out.println(minDistance("sea", "eat"));
    }

    public static int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        if (m == 0) {
            return n;
        }
        if (n == 0) {
            return m;
        }
        // status[i][j]表示s[0-i] 变成t[0-j]所需的最小的编辑距离
        int[][] status = new int[m][n];
        char[] s = word1.toCharArray();
        char[] t = word2.toCharArray();
        init(m, n, status, s, t);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (s[i] != t[j]) {
                    status[i][j] = min(status[i - 1][j], status[i-1][j-1], status[i][j - 1]) + 1;
                } else {
                    status[i][j] = min(status[i - 1][j] + 1, status[i][j - 1] + 1, status[i - 1][j - 1]);
                }
            }
        }
        return status[m - 1][n - 1];
    }

    private static void init(int m, int n, int[][] status, char[] s, char[] t) {
        // 表示是否遇到了s[0]或t[0]是否在另一个字符串里面遇到了重复的字符
        boolean flag1 = true;
        boolean flag2 = true;
        if (s[0] != t[0]) {
            status[0][0] = 1;
        } else {
            flag1 = false;
            flag2 = false;
        }
        // 初始化 表示t[0] 想要变成s[0-j]所需要多少次编辑（注意会有重复的）
        for (int i = 1; i < m; i++) {
            if (t[0] == s[i] && flag1) {
                status[i][0] = status[i - 1][0];
                flag1 = false;
            } else {
                status[i][0] = status[i - 1][0] + 1;
            }
        }
        // 初始化 表示s[0] 想要变成t[0-j]所需要多少次编辑（注意会有重复的）
        for (int j = 1; j < n; j++) {
            if (s[0] == t[j] && flag2) {
                status[0][j] = status[0][j - 1];
                flag2 = false;
            } else {
                status[0][j] = status[0][j - 1] + 1;
            }
        }
    }

    private static int min(int a, int b, int c) {
        int min = a < b ? a : b;
        return min < c ? min : c;
    }
}

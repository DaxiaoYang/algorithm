package com.algorithm.dynamicprogramming;

public class EditDistance {

    private static int minDist = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] a = "mitcmu".toCharArray();
        char[] b = "mtacnu".toCharArray();
        getEditDistance(0,0,0,a,a.length, b, b.length);
        System.out.println(minDist);
        System.out.println(getEditDistance(a, b));
    }

    // 回溯法实现最小编辑距离
    public static void getEditDistance(int i, int j, int edit, char[] a, int m, char[] b, int n) {
        if (i == m || j == n) {
            edit += m - i;
            edit += n - j;
            minDist = Math.min(minDist, edit);
            return;
        }
        if (a[i] == b[j]) {
            getEditDistance(i + 1, j + 1, edit, a,m,b,n);
        } else {
            getEditDistance(i + 1, j, edit + 1, a,m,b,n);
            getEditDistance(i, j + 1, edit + 1, a,m,b,n);
            getEditDistance(i + 1, j + 1, edit + 1, a,m,b,n);
        }
    }

    // dp编辑距离
    public static int getEditDistance(char[] a, char[] b) {
        // 初始化状态表
        int m = a.length;
        int n = b.length;
        // states[i][j] 存储的是使得a[0,i]与b[0,j]变得相同 所需进行的最小的编辑次数
        int[][] states = new int[m][n];
        states[0][0] = a[0] == b[0] ? 0 : 1;
        for (int i = 1; i < m; i++) {
            states[i][0] = states[i - 1][0] + 1;
        }
        for (int j = 1; j < n; j++) {
            states[0][j] = states[0][j - 1] + 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (a[i] != b[j]) {
                    states[i][j] = min(states[i - 1][j], states[i][j - 1], states[i - 1][j - 1]) + 1;
                } else {
                    states[i][j] = min(states[i - 1][j] + 1, states[i][j - 1] + 1, states[i - 1][j - 1]);
                }
            }
        }
        return states[m - 1][n  - 1];
    }

    private static int min(int a, int b, int c) {
        int min = a < b ? a : b;
        if (c < min) {
            min = c;
        }
        return min;
    }


    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        // 1.dp[i][j] A[0,i-1]到B[0,j-1]的最小编辑距离
        int[][] dp = new int[len1 + 1][len2 + 1];
        // 3.
        for (int i = 1; i <= len1; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= len2; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                // 2. 两种情况 A[i-1] == / != B[j-1]
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // 不需要额外的编辑距离 a a -> ab ab
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    /*
                        a a -> ab ac
                        不同时 有三种处理方式：
                        1. 删A[i-1] dp[i-1][j] + 1
                        2. 删B[j-1] dp[i][j-1] + 1
                        3. 替换A[i-1]/B[j-1] dp[i-1][j-1] + 1
                     */
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
            }
        }
        return dp[len1][len2];
    }
}

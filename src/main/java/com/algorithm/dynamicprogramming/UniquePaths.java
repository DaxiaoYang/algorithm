package com.algorithm.dynamicprogramming;

import java.util.Arrays;

public class UniquePaths {

    // 一维数组
    public int uniquePathsDp2(int m, int n) {
        int[] status = new int[n];
        Arrays.fill(status, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                status[j] = status[j - 1] + status[j];
            }
        }
        return status[n - 1];
    }

    // 二维数组
    public int uniquePathsDp(int m, int n) {
        int[][] memo = new int[m][n];
        // 最左边一列和最上面一排都只有一种方式到达
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    memo[i][j] = 1;
                } else {
                    memo[i][j] = memo[i - 1][j] + memo[i][j - 1];
                }
            }
        }
        return memo[m - 1][n - 1];
    }

    // dfs + memo
    public static int uniquePaths(int m, int n) {
        int[][] memo = new int[m][n];
        // 最左边一列和最上面一排都只有一种方式到达
        for (int i = 0; i < m; i++) {
            memo[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            memo[0][j] = 1;
        }
        return dfs(m - 1, n - 1, memo);
    }

    private static int dfs(int i, int j, int[][] memo) {
        // 因为上面给左上角的边界都赋值了 所以不用判断是否越界 类似与哨兵的思想
        // 不计算重复子问题
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        //  num(i,j) = num(i-1,j) + num(i,j-1)
        int res = dfs(i - 1, j, memo) + dfs(i, j - 1, memo);
        memo[i][j] = res;
        return res;
    }
}

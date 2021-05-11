package com.algorithm.dynamicprogramming;

public class MinimumPathSum {

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        /*
            (i,j)表示到达grid[i][j]的最短路径长
            (i,j) = min[(i-1, j), (i,j-1)] + [i,j] i > 0 j > 0
                  = (i-1,j) + [i,j] j = 0, i > 0
                  = (i, j - 1) + [i,j] i = 0, j > 0
         */
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j > 0) {
                    grid[i][j] += grid[i][j - 1];
                } else if (j == 0 && i > 0) {
                    grid[i][j] += grid[i - 1][j];
                } else if (i > 0 && j > 0){
                    grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
                }
            }
        }
        return grid[m - 1][n - 1];
    }

    public static void main(String[] args) {

    }

    public static int minPathSumDfs(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] memo = new int[m][n];
        memo[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            memo[i][0] = grid[i][0] + memo[i - 1][0];
        }
        for (int j = 1; j < n; j++) {
            memo[0][j] = grid[0][j] + memo[0][j - 1];
        }
        return dfs(m - 1, n - 1, memo, grid);
    }

    private static int dfs(int i, int j, int[][] memo, int[][] grid) {
        // 这个条件不能少 会有一个[[0]]的edge case导致第二个判断认为没有计算过进入dfs导致数组索引越界
        if (i == 0 || j == 0) {
            return memo[i][j];
        }
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        memo[i][j] = grid[i][j] + Math.min(dfs(i - 1, j, memo, grid), dfs(i, j -1, memo, grid));
        return memo[i][j];
    }
}

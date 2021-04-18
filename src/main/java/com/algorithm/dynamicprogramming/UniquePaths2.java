package com.algorithm.dynamicprogramming;

public class UniquePaths2 {

    public static void main(String[] args) {
        int[][] grid = {{0,0,0},{0,1,0},{0,0,0}};
        System.out.println(uniquePathsWithObstacles(grid));
        System.out.println(uniquePathsWithObstaclesOneDimension(grid));
    }


    public static int uniquePathsWithObstaclesOneDimension(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] status = new int[n];
        for (int j = 0; j < n && obstacleGrid[0][j] != 1; j++) {
            status[j] = 1;
        }
        for (int i = 1; i < m; i++) {
            // 最左边的也要设置 因为有障碍的话还是到不了 path = 0
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    status[j] = 0;
                } else if (j != 0) {
                    // j = 0 时 status[j] = status[j] (对应二维数组中的status[i][j] = status[i-1][j])
                    status[j] = status[j - 1] + status[j];
                }
            }
        }
        return status[n - 1];
    }

    /**
     *
     * 状态转移方程
     * (i,j) = (i-1,j) + (i,j-1) (grid[i][j] != 1)
     * (i,j) = 0 (grid[i][j] == 1)
     */
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] status = new int[m][n];
        for (int i = 0; i < m && obstacleGrid[i][0] != 1; i++) {
            status[i][0] = 1;
        }
        for (int j = 0; j < n && obstacleGrid[0][j] != 1; j++) {
            status[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    status[i][j] = status[i - 1][j] + status[i][j - 1];
                }
            }
        }
        return status[m - 1][n - 1];
    }
}

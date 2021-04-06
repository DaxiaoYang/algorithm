package com.algorithm.dynamicprogramming;

/**
 * “杨辉三角”不知道你听说过吗？我们现在对它进行一些改造。
 * 每个位置的数字可以随意填写，经过某个数字只能到达下面一层相邻的两个数字。
 * 假设你站在第一层，往下移动，我们把移动到最底层所经过的所有数字之和，定义为路径的长度。
 * 请你编程求出从最高层移动到最底层的最短路径长度。
 */
public class YangHuiDP {

    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int[][] rows = {{5}, {7,8}, {2,3,4}};
        backtrack(0, 0, 0, rows.length, rows);
        System.out.println(min);
        System.out.println(dp(rows));
        System.out.println(dp2(rows));
    }

    /**
     * 当前状态 (i, j, val) val表示到达(i,j)位置时所走的路径长度
     */
    public static void backtrack(int i, int j, int val, int n, int[][] rows) {
        // 走到叶子节点
        if (i == n) {
            min = Math.min(min, val);
            return;
        }
        val += rows[i][j];
        // 每次可以选择两个方向 下方或者右下方
        backtrack(i + 1, j, val, n, rows);
        backtrack(i + 1, j + 1, val, n, rows);
    }

    public static int dp(int[][] rows) {
        int n = rows.length;
        int[][] states = new int[n][n];
        states[0][0] = rows[0][0];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (states[i - 1][j] != 0) {
                    int valLeft = states[i - 1][j] + rows[i][j];
                    int valRight = states[i - 1][j] + rows[i][j + 1];
                    states[i][j] = states[i][j] == 0 ? valLeft : Math.min(states[i][j], valLeft);
                    states[i][j + 1] = states[i][j + 1] == 0 ? valRight : Math.min(states[i][j + 1], valRight);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            min = Math.min(min, states[n - 1][i]);
        }
        return min;
    }

    public static int dp2(int[][] rows) {
        int n = rows.length;
        int[][] states = new int[n][n];
        // 初始化
        states[0][0] = rows[0][0];
        // 状态转移方程： val[i, j] = min(val[i-1,j], val[i-1,j-1]) + [i,j]
        // val[i,j]的状态只可能是从上方或者是左上方这两个方向过来
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                // 最左边和最右边都只有一条路径
                if (j == 0) {
                    states[i][j] = states[i - 1][j] + rows[i][j];
                } else if (j == i) {
                    states[i][j] = states[i - 1][j - 1] + rows[i][j];
                } else {
                    // 中间的才有两条路径
                    states[i][j] = Math.min(states[i - 1][j], states[i - 1][j - 1]) + rows[i][j];
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            min = Math.min(min, states[n - 1][i]);
        }
        return min;
    }
}

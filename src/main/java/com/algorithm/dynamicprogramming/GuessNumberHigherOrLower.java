package com.algorithm.dynamicprogramming;

public class GuessNumberHigherOrLower {

    public static void main(String[] args) {
        System.out.println(getMoneyAmount(10));
    }

    public static int getMoneyAmount(int n) {
        // status[i][j]表示计算[i,j]这个范围的最少金额
        int[][] status = new int[n + 1][n + 1];
        return dfs(status, 1, n);
    }

    private static int dfs(int[][] status, int start, int end) {
        // 表示超过合法范围或者 或者是缩小范围到一个数（即该数就是所猜的数）
        if (start >= end) {
            return 0;
        }
        // 已经计算过的[i][j]
        if (status[start][end] != 0) {
            return status[start][end];
        }
        int res = Integer.MAX_VALUE;
        // i in [start,end] (i) = i + max(status[start][i-1], status[i+1][end])
        // 假设每次选择的数都不中 就是非叶子节点都不中 所以才会到叶子节点 而且取左右两个分支中最大的值来保证取的都是最坏情况
        for (int i = start; i <= end; i++) {
            int temp = i + Math.max(dfs(status, start, i - 1), dfs(status, i + 1, end));
            // status[start][end] = min((i)) 取所有最坏情况中成本最低的一项
            // minimizing the possible loss for all worst case (maximum loss) scenarios.
            res = Math.min(res, temp);
        }
        status[start][end] = res;
        return res;
    }
}

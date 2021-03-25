package com.algorithm.dynamicprogramming;

public class KnapSackDP {

    public static void main(String[] args) {
        int[] a = {1,2,3};
        System.out.println(getMaxWeight(a, 3, 4));
    }

    public static int getMaxWeight(int[] weights, int n, int limit) {
        // 状态矩阵 n表示要决策的阶段 每个阶段决定是否要选择当前物品：
        // 矩阵中的每一行存储的都是 当前阶段能够达到的状态（不重复 如果用回溯法是会有重复的状态 也就是重复的计算子问题）
        boolean[][] states = new boolean[n][limit + 1];
        // 初始化
        states[0][0] = true;
        if (weights[0] <= limit) {
            states[0][weights[0]] = true;
        }
        // 状态转移方程
        for (int i = 1; i < n; i++) {
            // 当前阶段的状态 都是基于上一个阶段的状态推导而来
            // 不选择当前物品 那就直接继承上一个阶段的状态
            for (int j = 0; j <= limit; j++) {
                if (states[i - 1][j]) {
                    states[i][j] = true;
                }
            }
            // 选择当前物品 那就加上当前物品的重量 同时注意边界 j（前一个阶段的累计重量） + weights[i] <= limit
            for (int j = 0; j <= limit - weights[i]; j++) {
                if (states[i - 1][j]) {
                    states[i][j + weights[i]] = true;
                }
            }
        }
        // 找出最终阶段的重量的最大值
        for (int j = limit; j >= 0; j--) {
            if (states[n - 1][j]) {
                return j;
            }
        }
        return 0;
    }
}

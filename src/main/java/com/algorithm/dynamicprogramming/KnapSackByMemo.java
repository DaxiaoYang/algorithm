package com.algorithm.dynamicprogramming;

/**
 * 回溯法 + memo 解决0-1背包问题
 */
public class KnapSackByMemo {

    private static int maxVal;
    private static int[] weight = {1,2,3};
    private static boolean[][] memo = new boolean[3][5];
    private static int n = 3;
    private static int limit = 4;

    public static void main(String[] args) {
        dfs(0, 0);
        System.out.println(maxVal);
    }

    /**
     * 计算当前状态
     * @param i 表示选择要对哪个物品进行决策：选还是不选
     * @param currentWeight 当前已选择的物品重量和
     */
    public static void dfs(int i, int currentWeight) {
        // 走到叶子节点
        if (currentWeight == limit || i == n) {
            maxVal = Math.max(maxVal, currentWeight);
            return;
        }
        // 如果是重复的状态 直接返回 因为之前计算过了：避免计算重复子问题
        if (memo[i][currentWeight]) {
            return;
        }
        // 记录状态
        memo[i][currentWeight] = true;
        // 不选择当前物品
        dfs(i + 1, currentWeight);
        // 选择当前物品
        if (currentWeight + weight[i] <= limit) {
            dfs(i + 1, currentWeight + weight[i]);
        }
    }
}

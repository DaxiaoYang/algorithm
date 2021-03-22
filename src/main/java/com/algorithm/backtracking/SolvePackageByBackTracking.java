package com.algorithm.backtracking;

/**
 * 回溯法解决0-1背包问题
 */
public class SolvePackageByBackTracking {

    private static int max;

    public static void main(String[] args) {
        int[] a = {3,4,5,7};
        dfs(0, 0, a, 11);
        System.out.println(max);
    }

    /**
     *
     * @param i 当前所选项的下标
     * @param currentWeight 当前重量
     * @param items 物件重量
     * @param limit 重量上限
     */
    public static void dfs(int i, int currentWeight, int[] items, int limit) {
        // 重量达到上限或者已经找了一个解（即走到了叶子节点）
        if (currentWeight == limit || i == items.length) {
            // 更新最大值
            max = Math.max(currentWeight, max);
            return;
        }
        // 对于每一个物件 都有两种选择 选或者不选
        // 这里是不选当前items[i]的分支
        dfs(i + 1, currentWeight, items, limit);
        // 这里是选择当前items[i]的分支 并且进行搜索剪枝：如果加上所选择的物件重量之后超出重量 就不继续进行递归遍历
        if (currentWeight + items[i] <= limit) {
            dfs(i + 1, currentWeight + items[i], items, limit);
        }
    }
}

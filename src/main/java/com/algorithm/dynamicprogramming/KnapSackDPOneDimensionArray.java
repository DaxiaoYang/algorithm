package com.algorithm.dynamicprogramming;

/**
 * 一维数组DP解决0-1背包问题
 */
public class KnapSackDPOneDimensionArray {

    public static void main(String[] args) {
        int[] a = {1,2,3};
        System.out.println(getMaxWeight(a, 3, 4));
    }

    public static int getMaxWeight(int[] weights, int n, int limit) {
        // 用一维数组 记录当前阶段所有可达的不重复的状态 即可
        // 计算当前阶段的状态时 上一个阶段的状态都已经存储在了 states数组中
        boolean[] states = new boolean[limit + 1];
        // 初始化
        states[0] = true;
        if (weights[0] <= limit) {
            states[weights[0]] = true;
        }
        // 状态转移方程
        for (int i = 1; i < n; i++) {
            // 这里从后往前计算的原因是 当前状态的产生是依赖于前一个阶段的状态
            // 如果从前往后计算 那么当前状态的产生就会受到刚生成的当前的状态影响
            for (int j = limit - weights[i]; j >= 0 ; j--) {
                // 选择当前物品
                if (states[j]) {
                    states[j + weights[i]] = true;
                }
                // 不选择当前物品 直接继承状态 states[j] = states[j]所以不用进行操作
            }
        }
        for (int j = limit; j >= 0; j--) {
            if (states[j]) {
                return j;
            }
        }
        return 0;
    }
}

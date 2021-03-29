package com.algorithm.dynamicprogramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 找到一堆商品中>=200的最小组合
 */
public class KnapSackCart {

    public static void main(String[] args) {
        int[] values = {60,150,30,40};
    }

    public static List<Integer> getCartList(int[] values, int n, int limit) {
        int upperBound = limit + 50;
        // 状态 n表示对n个阶段对商品进行决策 是否选择该商品
        boolean[][] states = new boolean[n][upperBound + 1];
        // 初始化
        states[0][0] = true;
        if (values[0] <= upperBound) {
            states[0][values[0]] = true;
        }
        // 状态转移方程
        for (int i = 1; i < n; i++) {
            // 对i商品进行决策
            // 不选择当前商品 直接继承状态
            for (int j = 0; j <= upperBound; j++) {
                if (states[i - 1][j]) {
                    states[i][j] = true;
                }
            }
            // 选择当前商品 基于上一个阶段的状态进行改变
            for (int j = 0; j <= upperBound - values[i]; j++) {
                if (states[i - 1][j]) {
                    states[i][j + values[i]] = true;
                }
            }
        }
        // 找出所选择的商品的集合
        List<Integer> goodIds = new LinkedList<>();
        // 找到最终落脚点
        int j;
        for (j = 200; j <= upperBound; j++) {
            if (states[n - 1][j]) {
                break;
            }
        }
        // 没有符合的组合
        if (j > upperBound) {
            return Collections.emptyList();
        }
        /**
         * 状态 (i, j) 只有可能从 (i-1, j) 或者 (i-1, j-value[i]) 两个状态推导过来。
         * 所以，我们就检查这两个状态是否是可达的，也就是 states[i-1][j]
         * 或者 states[i-1][j-value[i]]是否是 true。
         * 如果 states[i-1][j]可达，就说明我们没有选择购买第 i 个商品，
         * 如果 states[i-1][j-value[i]]可达，那就说明我们选择了购买第 i 个商品
         */
        // 从末尾 倒推 j指向的位置一直是true的 但是为true不代表选择了当前商品
        // 只有当前的状态是基于上一个阶段的状态 选择了当前商品后 状态发生了改变 才能说明选择了当前的商品
        for (int i = n - 1; i >= 1; i--) {
            if (j - values[i] >= 0 && states[i - 1][j - values[i]]) {
                // 表明选择了当前的商品
                j = j - values[i];
                goodIds.add(0, i);
            }
            // 否则就是只是继承上一个阶段的状态 没有选择当前商品 j的值不变
        }
        // j = 0表示不选择第一个商品
        if (j != 0) {
            goodIds.add(0, 0);
        }
        return goodIds;
    }
}

package com.algorithm.dynamicprogramming;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/9/7
 */
public class UniquePathSearchTrees {

    public int numTrees(int n) {
        // 1.dp[i] 表示i个不同值的结点 组成的不同构造的二叉搜索树的数量
        int[] dp = new int[n + 1];
        // 3.dp[0] = 1 空树也是一颗二叉搜索树
        dp[0] = 1;
        // 2. dp[i] += dp[j - 1] * dp[i - j] j=[1,i] j表示以j为该二叉搜索树的头结点
        // dp[j - 1]表示j为头结点时 左子树中二叉树的可能构造树
        // dp[i - j]表示j为头结点时 右子树中二叉树的可能构造树
        // 4.->
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }
}

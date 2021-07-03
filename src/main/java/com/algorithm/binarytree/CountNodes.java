package com.algorithm.binarytree;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/6/19
 * 计算完全二叉树的结点数 时间复杂度 logn * logn
 */
public class CountNodes {

    public int countNodes(TreeNode root) {
        // 一直左走 计算树的深度
        int leftDepth = leftDepth(root);
        // 一直右走 计算树的深度
        int rightDepth = rightDepth(root);
        // 相同说明该树（子树）是满二叉树 n = 2^h - 1
        if (leftDepth == rightDepth) {
            return (1 << leftDepth) - 1;
        }
        // 不为满二叉树 递归计算左子树结点 和 右子树结点
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    private int leftDepth(TreeNode root) {
        int depth = 0;
        while (root != null) {
            depth++;
            root = root.left;
        }
        return depth;
    }

    private int rightDepth(TreeNode root) {
        int depth = 0;
        while (root != null) {
            depth++;
            root = root.right;
        }
        return depth;
    }
}

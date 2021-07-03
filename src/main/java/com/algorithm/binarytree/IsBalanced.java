package com.algorithm.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/6/20
 */
public class IsBalanced {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        root.left = left;
        TreeNode right = new TreeNode(2);
        root.right = right;
        TreeNode leftLeft = new TreeNode(3);
        left.left = leftLeft;
        leftLeft.left = new TreeNode(4);
        TreeNode rightRight = new TreeNode(3);
        right.right = rightRight;
        rightRight.left = new TreeNode(4);
        System.out.println(isBalanced(root));
    }

    public static boolean isBalanced(TreeNode root) {
        List<Boolean> isBalanced = new ArrayList<>(1);
        isBalanced.add(true);
        isBalanced(root, isBalanced);
        return isBalanced.get(0);
    }

    private static void isBalanced(TreeNode root, List<Boolean> isBalanced) {
        if (root == null) {
            return;
        }
        // 剪枝
        if (!isBalanced.get(0)) {
            return;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        if (Math.abs(leftDepth - rightDepth) > 1) {
            isBalanced.set(0, false);
            return;
        }
        isBalanced(root.left, isBalanced);
        isBalanced(root.right, isBalanced);
    }

    private static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    public boolean isBalancedDepth(TreeNode root) {
        return maxDepthDepth(root) != -1;
    }

    private int maxDepthDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 左
        int leftDepth = maxDepth(root.left);
        // 用-1来标记root为根结点的子树不是 平衡二叉树
        if (leftDepth == -1) {
            return -1;
        }
        // 右
        int rightDepth = maxDepth(root.right);
        if (rightDepth == -1) {
            return -1;
        }
        // 中
        return Math.abs(leftDepth - rightDepth) > 1 ? -1 : 1 + Math.max(leftDepth, rightDepth);
    }
}

package com.algorithm.binarytree;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/7/13
 */
public class TrimBST {

    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        // 说明当前节点和其左子树中的所有节点（BST性质）都需要被去除掉 返回右子树（剪枝）
        if (root.val < low) {
            return trimBST(root.right, low, high);
        }
        // 说明当前节点和其右子树中的节点都需要被去除掉 返回左子树
        if (root.val > high) {
            return trimBST(root.left, low, high);
        }
        // 当前节点在该范围内 其左右子树中可能有需要去除的节点 递归处理左右子树
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        // 该节点在范围内 所以得到保留
        return root;
    }
}

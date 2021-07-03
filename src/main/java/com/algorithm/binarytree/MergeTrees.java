package com.algorithm.binarytree;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/7/1
 */
public class MergeTrees {

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        TreeNode root = null;
        if (root1 == null && root2 == null) {
            return root;
        }
        int rootVal;
        if (root1 == null) {
            rootVal = root2.val;
        } else if (root2 == null) {
            rootVal = root1.val;
        } else {
            rootVal = root1.val + root2.val;
        }
        root = new TreeNode(rootVal);
        root.left = mergeTrees(getChild(root1, true), getChild(root2, true));
        root.right = mergeTrees(getChild(root1, false), getChild(root2, false));
        return root;
    }

    private TreeNode getChild(TreeNode node, boolean isLeft) {
        if (node == null) {
            return null;
        }
        return isLeft ? node.left : node.right;
    }
}

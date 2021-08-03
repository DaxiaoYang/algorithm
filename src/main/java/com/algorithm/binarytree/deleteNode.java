package com.algorithm.binarytree;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/7/12
 */
public class deleteNode {

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (key > root.val) {
            // 递归去右子树找
            root.right = deleteNode(root.right, key);
        } else if (key < root.val) {
            // 递归去左子树找
            root.left = deleteNode(root.left, key);
        } else {
            /*
                当前根结点的值为目标元素后分4种情况
                1. 该结点为叶子结点：返回null
                2. 该结点只有左子树：返回右子树
                3. 该结点只有右子树：返回左子树
                4. 该结点两个子树都有：找到后继 将后继的值赋值给当前结点 递归在右子树中删除后继
             */
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            TreeNode next = getMin(root.right);
            root.val = next.val;
            root.right = deleteNode(root.right, root.val);
        }
        return root;
    }

    private TreeNode getMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}

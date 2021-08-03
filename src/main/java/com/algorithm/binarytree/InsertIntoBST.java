package com.algorithm.binarytree;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/7/6
 */
public class InsertIntoBST {

    /**
     * 将val插入当前以root为根结点的子树中
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        // 子树为空 则该值成为根结点
        if (root == null) {
            return new TreeNode(val);
        }
        if (val > root.val) {
            // 将值插入右子树
            root.right = insertIntoBST(root.right, val);
        } else {
            // 将值插入左子树
            root.left =  insertIntoBST(root.left, val);
        }
        // 返回根结点
        return root;
    }

    public TreeNode insertIntoBSTIter(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        // 记录上一个访问到的节点
        TreeNode prev = null;
        TreeNode curr = root;
        // 退出循环时 curr为null prev为最后一个遍历到的节点
        while (curr != null) {
            prev = curr;
            if (val > curr.val) {
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }
        // 根据插入值来确定插入的是prev的左子树还是右子树
        if (val < prev.val) {
            prev.left = new TreeNode(val);
        } else {
            prev.right = new TreeNode(val);
        }
        return root;
    }
}

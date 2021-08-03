package com.algorithm.binarytree;

import java.util.LinkedList;
import java.util.List;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/7/4
 */
public class MorrisInorderTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode left = new TreeNode(1);
        root.left = left;
        left.right = new TreeNode(2);
        TreeNode right = new TreeNode(5);
        root.right = right;
        right.left = new TreeNode(4);
        System.out.println(morrisTraversal(root));
    }

    /**
     * 1. Initialize current as root
     * 2. While current is not NULL
     *    If the current does not have left child
     *       a) Print current’s data
     *       b) Go to the right, i.e., current = current->right
     *    Else
     *       a) Find rightmost node in current left subtree OR
     *               node whose right child == current.
     *          If we found right child == current
     *              a) Update the right child as NULL of that node whose right child is current
     *              b) Print current’s data
     *              c) Go to the right, i.e. current = current->right
     *          Else
     *              a) Make current as the right child of that rightmost
     *                 node we found; and
     *              b) Go to this left child, i.e., current = current->left
     * @param root Tree root
     * @return inorder sequence
     */
    public static List<Integer> morrisTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        TreeNode curr =  root;
        TreeNode prev;
        while (curr != null) {
            // 没有左子树 访问当前节点的值 LNR中的N
            if (curr.left == null) {
                res.add(curr.val);
                // 向右走 LNR中的R 或者是向中序的后继节点走
                curr = curr.right;
            } else {
                // 有左子树 则在左子树中找到中序遍历的前驱节点 为了构造或还原
                prev = curr.left;
                // 前驱节点的右子树为空 或者右子树指向当前节点（已经被线性化过）
                while (prev.right != null && prev.right != curr) {
                    prev = prev.right;
                }
                // 第一次访问到前驱
                if (prev.right == null) {
                    // 中序前驱的right指针指向其后继
                    prev.right = curr;
                    // 向左走 继续线性化左子树
                    curr = curr.left;
                } else {
                    // 第二次访问到前驱 即现在是prev.right == curr 还原结构
                    prev.right = null;
                    // 第二次访问到前驱 说明是通过 前驱节点的curr = curr.right过来的
                    // 前驱节点已经被访问 所以访问当前节点
                    res.add(curr.val);
                    // 向右走
                    curr = curr.right;
                }
            }
        }
        return res;
    }
}

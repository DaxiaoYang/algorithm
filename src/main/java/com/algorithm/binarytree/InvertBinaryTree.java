package com.algorithm.binarytree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/6/17
 */
public class InvertBinaryTree {

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 交换左右孩子指针的值（是一个局部变换）
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        // 递归处理左子树
        root.left = invertTree(root.left);
        // 递归处理右子树
        root.right = invertTree(root.right);
        // 返回根结点
        return root;
    }


    public TreeNode invertTreeIter(TreeNode root) {
        if (root == null) {
            return null;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        TreeNode temp, curr;
        while (!stack.isEmpty()) {
            curr = stack.pop();
            // 实际上就是交换每个结点的左右孩子结点的值
            temp = curr.left;
            curr.left = curr.right;
            curr.right = temp;
            if (curr.left != null) {
                stack.push(curr.left);
            }
            if (curr.right != null) {
                stack.push(curr.right);
            }
        }
        return root;
    }
}

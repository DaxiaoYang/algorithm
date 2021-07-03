package com.algorithm.binarytree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/6/17
 */
public class SymmetricTree {

    public boolean isSymmetric(TreeNode root) {
        return root == null || isSymmetric(root.left, root.right);
    }

    /**
     * 比较的单元是左右两个结点是否相同
     * @param left 左子树中的结点
     * @param right 右子树中的结点
     */
    private boolean isSymmetric(TreeNode left, TreeNode right) {
        // 说明两个都为null
        if (left == right) {
            return true;
        }
        // 两个中有一个是null 说明不对称
        if (left == null || right == null) {
            return false;
        }
        // 两个结点值不同 不对称
        if (left.val != right.val) {
            return false;
        }
        // 对应的子树中的 两个结点也相同
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

    public boolean isSymmetricIter(TreeNode root) {
        if (root == null) {
            return true;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root.left);
        stack.push(root.right);
        TreeNode left;
        TreeNode right;
        while (!stack.isEmpty()) {
            // 每次比较的单位都是两个左右结点 比较它们是否相同
            left = stack.pop();
            right = stack.pop();
            // 表示两个都为空 到达叶子结点 跳过继续遍历其他的
            if (left == right) {
                continue;
            }
            if (left == null || right == null || left.val != right.val) {
                return false;
            }
            stack.push(left.right);
            stack.push(right.left);
            stack.push(right.right);
            stack.push(left.left);
        }
        return true;
    }
}

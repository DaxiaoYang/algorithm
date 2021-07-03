package com.algorithm.binarytree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/6/24
 */
public class SumOfLeftLeaves {

    /**
     * 返回以root为根结点的子树下的所有左叶子的数值的和
     * @param root
     * @return
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int sum = 0;
        // 如果左孩子是叶子结点 直接相加
        if (isLeaf(root.left)) {
            sum += root.left.val;
        } else {
            // 左孩子是非叶子结点
            sum += sumOfLeftLeaves(root.left);
        }
        // 仅当右孩子是非叶子结点时才遍历 如果是叶子结点就不计算
        if (!isLeaf(root.right)) {
            sum += sumOfLeftLeaves(root.right);
        }
        // 返回以root为根结点的子树下的所有左叶子的数值的和
        return sum;
    }

    public int sumOfLeftLeavesIter(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int sum = 0;
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        TreeNode curr;
        while (!stack.isEmpty()) {
            curr = stack.pop();
            if (curr.left != null) {
                if (isLeaf(curr.left)) {
                    sum += curr.left.val;
                } else {
                    stack.push(curr.left);
                }
            }
            if (curr.right != null && !isLeaf(curr.right)) {
                stack.push(curr.right);
            }
        }
        return sum;
    }

    private boolean isLeaf(TreeNode node) {
        if (node != null && node.left == null && node.right == null) {
            return true;
        }
        return false;
    }
}

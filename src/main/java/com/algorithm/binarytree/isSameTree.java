package com.algorithm.binarytree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/6/22
 */
public class isSameTree {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == q) {
            return true;
        }
        if (p == null || q == null || p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public boolean isSameTreeIter(TreeNode p, TreeNode q) {
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(q);
        stack.push(p);
        TreeNode left, right;
        while (!stack.isEmpty()) {
            left = stack.pop();
            right = stack.pop();
            // 空结点或者两个引用相同 跳过
            if (left == right) {
                continue;
            }
            // 其中一个为空 或者两个不为空但是值不同
            if (left == null || right == null || left.val != right.val) {
                return false;
            }
            // 将对应子树结点放入栈中
            stack.push(right.right);
            stack.push(left.right);
            stack.push(right.left);
            stack.push(left.left);
        }
        return true;
    }
}

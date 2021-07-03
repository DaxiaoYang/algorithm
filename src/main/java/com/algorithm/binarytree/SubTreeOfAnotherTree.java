package com.algorithm.binarytree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/6/22
 */
public class SubTreeOfAnotherTree {

    /**
     * 递归 也是在先序遍历中判断当前子树与给定子树是否相同
     * @param root
     * @param subRoot
     * @return
     */
    public boolean isSubtreeRecur(TreeNode root, TreeNode subRoot) {
        if (root == null) {
            return false;
        }
        if (isSameTree(root, subRoot)) {
            return true;
        }
        // 关键在于 或 只要找到一个相同的子树即可
        return isSubtreeRecur(root.left, subRoot) || isSubtreeRecur(root.right, subRoot);
    }

    /**
     * 非递归 在先序遍历中判断每一个结点与子树结点是否相同
     * @param root
     * @param subRoot
     * @return
     */
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode curr;
        stack.push(root);
        while (!stack.isEmpty()) {
            curr = stack.pop();
            if (isSameTree(curr, subRoot)) {
                return true;
            }
            // 空结点就不比较了 比较到叶子结点为止
            if (curr.right != null) {
                stack.push(curr.right);
            }
            if (curr.left != null) {
                stack.push(curr.left);
            }
        }
        return false;
    }

    private boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == q) {
            return true;
        }
        if (p == null || q == null || p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}

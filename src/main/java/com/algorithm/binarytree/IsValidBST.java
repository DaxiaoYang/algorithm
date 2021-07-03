package com.algorithm.binarytree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/7/2
 */
public class IsValidBST {

    public boolean isValidBSTRange(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * 用以root为根结点的子树的必须在的范围做判定
     * @param root
     * @param low (low, high)
     * @param high
     * @return
     */
    private boolean isValidBST(TreeNode root, long low, long high) {
        if (root == null) {
            return true;
        }
        if (root.val <= low || root.val >= high) {
            return false;
        }
        // 对于根结点来说 左子树范围:(负无穷, root.val) 右子树范围(root.val， 正无穷)
        return isValidBST(root.left, low, root.val) && isValidBST(root.right, root.val, high);
    }

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        TreeNode prev = getInorderPrev(root);
        if (prev != null && root.val <= prev.val) {
            return false;
        }
        TreeNode next = getInorderNext(root);
        if (next != null && root.val >= next.val) {
            return false;
        }
        return isValidBST(root.left) && isValidBST(root.right);
    }

    public boolean isValidBSTIter(TreeNode root) {
        TreeNode prev = null;
        TreeNode curr = root;
        Deque<TreeNode> stack = new LinkedList<>();
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            // 中序遍历有序
            if (prev != null && curr.val <= prev.val) {
                return false;
            }
            prev = curr;
            curr = curr.right;
        }
        return true;
    }

    private TreeNode getInorderPrev(TreeNode root) {
        if (root.left == null) {
            return null;
        }
        TreeNode prev = root.left;
        while (prev.right != null) {
            prev = prev.right;
        }
        return prev;
    }

    private TreeNode getInorderNext(TreeNode root) {
        if (root.right == null) {
            return null;
        }
        TreeNode next = root.right;
        while (next.left != null) {
            next = next.left;
        }
        return next;
    }
}

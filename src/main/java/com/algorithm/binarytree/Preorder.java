package com.algorithm.binarytree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/6/9
 */
public class Preorder {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        preorder(res, root);
        return res;
    }

    private void preorder(List<Integer> res, TreeNode root) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        preorder(res, root.left);
        preorder(res, root.right);
    }

    public List<Integer> preorderTraversalIter(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        stack.push(root);
        TreeNode curr;
        while (!stack.isEmpty()) {
            curr = stack.pop();
            res.add(curr.val);
            if (curr.right != null) {
                stack.push(curr.right);
            }
            if (curr.left != null) {
                stack.push(curr.left);
            }
        }
        return res;
    }
}

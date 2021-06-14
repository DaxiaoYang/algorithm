package com.algorithm.binarytree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/6/9
 */
public class Postorder {

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        postorder(res, root);
        return res;
    }

    private void postorder(List<Integer> res, TreeNode root) {
        if (root == null) {
            return;
        }
        postorder(res, root.left);
        postorder(res, root.right);
        res.add(root.val);
    }

    public List<Integer> postorderTraversalIter(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        stack.push(root);
        TreeNode curr;
        while (!stack.isEmpty()) {
            curr = stack.pop();
            res.add(0, curr.val);
            if (curr.left != null) {
                stack.push(curr.left);
            }
            if (curr.right != null) {
                stack.push(curr.right);
            }
        }
        return res;
    }
}

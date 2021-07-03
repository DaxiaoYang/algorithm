package com.algorithm.binarytree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/7/3
 */
public class GetMinimumDifference {

    public int getMinimumDifference(TreeNode root) {
        TreeNode curr = root;
        TreeNode prev = null;
        int dif = Integer.MAX_VALUE;
        Deque<TreeNode> stack = new LinkedList<>();
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            if (prev != null) {
                dif = Math.min(dif, curr.val - prev.val);
            }
            curr = curr.right;
        }
        return dif;
    }

    public int getMinimumDifferenceDfs(TreeNode root) {
        // info[0] is prev value in inorder sequence while info[1] holds the min difference
        int[] info = new int[]{-1, Integer.MAX_VALUE};
        inorder(root, info);
        return info[1];
    }

    private void inorder(TreeNode root, int[] info) {
        if (root == null) {
            return;
        }
        inorder(root.left, info);
        // if the current node has a prev in inorder traversal
        if (info[0] != -1) {
            // update difference, since it's inorder, root.val is guranteed to be greater than prev in a BST
            info[1] = Math.min(info[1], root.val - info[0]);
        }
        // update prev node
        info[0] = root.val;
        inorder(root.right, info);
    }
}

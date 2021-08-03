package com.algorithm.binarytree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/7/15
 */
public class ConvertBST2GreaterTree {

    public TreeNode convertBST(TreeNode root) {
        dfs(root, new int[1]);
        return root;
    }

    private void dfs(TreeNode root, int[] sum) {
        if (root == null) {
            return;
        }
        // RNL遍历
        dfs(root.right, sum);
        root.val += sum[0];
        sum[0] = root.val;
        dfs(root.left, sum);
    }

    public TreeNode convertBSTIter(TreeNode root) {
        int sum = 0;
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.right;
            }
            curr = stack.pop();
            curr.val += sum;
            sum += curr.val;
            curr = curr.left;
        }
        return root;
    }
}

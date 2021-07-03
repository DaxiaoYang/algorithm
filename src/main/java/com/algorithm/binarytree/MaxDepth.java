package com.algorithm.binarytree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/6/18
 */
public class MaxDepth {

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public int maxDepthBfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode curr;
        int rowSize;
        int depth = 0;
        while (!queue.isEmpty()) {
            rowSize = queue.size();
            for (int i = 0; i < rowSize; i++) {
                curr = queue.poll();
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
            depth++;
        }
        return depth;
    }


    public int maxDepthDfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        // 记录遍历到的结点对应的深度
        Deque<Integer> depths = new LinkedList<>();
        stack.push(root);
        depths.push(1);
        TreeNode curr;
        int max = 0;
        int currDepth;
        while (!stack.isEmpty()) {
            curr = stack.pop();
            currDepth = depths.pop();
            max = Math.max(max, currDepth);
            if (curr.right != null) {
                stack.push(curr.right);
                depths.push(currDepth + 1);
            }
            if (curr.left != null) {
                stack.push(curr.left);
                depths.push(currDepth + 1);
            }
        }
        return max;
    }
}

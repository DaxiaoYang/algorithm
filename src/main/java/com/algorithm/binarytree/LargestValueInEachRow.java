package com.algorithm.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/6/14
 */
public class LargestValueInEachRow {

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode curr;
        int rowSize;
        while (!queue.isEmpty()) {
            int max = Integer.MIN_VALUE;
            rowSize = queue.size();
            for (int i = 0; i < rowSize; i++) {
                curr = queue.poll();
                max = Math.max(max, curr.val);
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
            res.add(max);
        }
        return res;
    }

    public List<Integer> largestValuesDfs(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(res, root, 0);
        return res;
    }

    private void dfs(List<Integer> res, TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        if (res.size() == depth) {
            res.add(Integer.MIN_VALUE);
        }
        if (root.val > res.get(depth)) {
            res.set(depth, root.val);
        }
        dfs(res, root.left, depth + 1);
        dfs(res, root.right, depth + 1);
    }
}

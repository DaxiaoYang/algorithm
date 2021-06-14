package com.algorithm.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/6/11
 */
public class Levelorder {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> rows = new ArrayList<>();
        if (root == null) {
            return rows;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode curr;
        while (!queue.isEmpty()) {
            int rowSize = queue.size();
            List<Integer> temp = new ArrayList<>(rowSize);
            for (int i = 0; i < rowSize; i++) {
                curr = queue.poll();
                temp.add(curr.val);
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
            rows.add(temp);
        }
        return rows;
    }

    public List<List<Integer>> levelOrderRecur(TreeNode root) {
        List<List<Integer>> rows = new ArrayList<>();
        dfs(rows, root, 0);
        return rows;
    }

    private void dfs(List<List<Integer>> rows, TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        if (rows.size() == depth) {
            rows.add(new LinkedList<>());
        }
        rows.get(depth).add(root.val);
        dfs(rows, root.left, depth + 1);
        dfs(rows, root.right, depth + 1);
    }
}

package com.algorithm.binarytree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/6/13
 */
public class BinaryTreeRightSightView {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        dfs(res, root, 0);
        return res;
    }

    private void dfs(List<Integer> res, TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        // NRL的方式遍历
        // 这里表示第一次到达新的一层
        if (res.size() == depth) {
            res.add(root.val);
        }
        dfs(res, root.right, depth + 1);
        dfs(res, root.left, depth + 1);
    }


    public List<Integer> rightSideViewBfs(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode curr;
        int rowSize;
        while (!queue.isEmpty()) {
            rowSize = queue.size();
            for (int i = 0; i < rowSize; i++) {
                curr = queue.poll();
                // 添加每一层的最右边的一个
                if (i == rowSize - 1) {
                    res.add(curr.val);
                }
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
        }
        return res;
    }
}

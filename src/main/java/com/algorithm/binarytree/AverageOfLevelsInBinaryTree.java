package com.algorithm.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/6/13
 */
public class AverageOfLevelsInBinaryTree {

    public static void main(String[] args) {
        System.out.println(1e20 + (-1e20 + 3.14));
    }

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode curr;
        int rowSize;
        double sum;
        while (!queue.isEmpty()) {
            sum = 0;
            rowSize = queue.size();
            for (int i = 0; i < rowSize; i++) {
                curr = queue.poll();
                sum += curr.val;
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
            res.add(sum / rowSize);
        }
        return res;
    }

    public List<Double> averageOfLevelsDfs(TreeNode root) {
        List<LevelNode> levels = new ArrayList<>();
        dfs(levels, root, 0);
        List<Double> res = new ArrayList<>(levels.size());
        for (LevelNode level : levels) {
            res.add(level.sum / level.count);
        }
        return res;
    }

    private void dfs(List<LevelNode> levels, TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        // 第一次到达和非第一次到达
        if (levels.size() == depth) {
            levels.add(new LevelNode(root.val, 1));
        } else {
            LevelNode level = levels.get(depth);
            level.sum += root.val;
            level.count++;
        }
        dfs(levels, root.left, depth + 1);
        dfs(levels, root.right, depth + 1);
    }

    /**
     * 用结点存储每一层的两个信息：总和 和 数量
     */
    private class LevelNode {
        double sum;
        int count;

        LevelNode(double sum, int count) {
            this.sum = sum;
            this.count = count;
        }
    }
}

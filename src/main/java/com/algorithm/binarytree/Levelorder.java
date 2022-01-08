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

    /***
     *
     * 二叉树先序遍历
     * @param rows 返回的层序遍历列表
     * @param root 要遍历的树的根结点
     * @param depth 深度 假定根结点深度为0
     */
    private void dfs(List<List<Integer>> rows, TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        // 说明第一次到达该层 建立该层的列表
        if (rows.size() == depth) {
            rows.add(new LinkedList<>());
        }
        // 每个结点都放到对应层次的列表中
        rows.get(depth).add(root.val);
        dfs(rows, root.left, depth + 1);
        dfs(rows, root.right, depth + 1);
    }
}

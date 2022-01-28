package com.algorithmlesson.binarytree;

import com.algorithm.binarytree.TreeNode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2022/1/13
 */
public class DiameterOfBinaryTree {


    public int diameterOfBinaryTree(TreeNode root) {
        int max = 0;
        Deque<TreeNode> stack = new LinkedList<>();
        Map<TreeNode, Integer> cache = new HashMap<>();
        if (root != null) {
            stack.push(root);
        }
        TreeNode curr;
        int diameter;
        // 先序遍历每个结点 求每个结点的直径 取最大值
        while (!stack.isEmpty()) {
            curr = stack.pop();
            diameter = getHeight(curr.left, cache) + getHeight(curr.right, cache);
            if (diameter > max) {
                max = diameter;
            }
            if (curr.right != null) {
                stack.push(curr.right);
            }
            if (curr.left != null) {
                stack.push(curr.left);
            }
        }
        return max;
    }

    private int getHeight(TreeNode root, Map<TreeNode, Integer> cache) {
        if (root == null) {
            return 0;
        }
        // 避免重复计算
        if (cache.containsKey(root)) {
            return cache.get(root);
        }
        int left = getHeight(root.left, cache);
        int right = getHeight(root.right, cache);
        int height = Math.max(left, right) + 1;
        cache.put(root, height);
        return height;
    }
}

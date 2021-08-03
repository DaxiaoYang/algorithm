package com.algorithm.binarytree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/7/14
 */
public class ConvertSortedArrayToBinarySearchTree {

    public TreeNode sortedArrayToBST(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }

    private TreeNode dfs(int[] nums, int low, int high) {
        if (low > high) {
            return null;
        }
        int mid = low + (high - low) / 2;
        // 每次都选取有序数组的中点作为根结点 这样左右子树的结点数之差就不会超过1
        TreeNode root = new TreeNode(nums[mid]);
        root.left = dfs(nums, low, mid - 1);
        root.right = dfs(nums, mid + 1, high);
        return root;
    }

    public TreeNode sortedArrayToBSTIter(int[] nums) {
        TreeNode root = new TreeNode(0);
        // 用一个stack装结点 Low high信息
        Deque<Object> stack = new LinkedList<>();
        TreeNode curr;
        int low, high, mid;
        stack.push(nums.length - 1);
        stack.push(0);
        stack.push(root);
        while (!stack.isEmpty()) {
            curr = (TreeNode) stack.pop();
            low = (int) stack.pop();
            high = (int) stack.pop();
            // 选取中点给当前子树根结点赋值
            mid = low + (high - low) / 2;
            curr.val = nums[mid];
            // 存在右子树
            if (mid + 1 <= high) {
                // 预先创建右子树根结点
                curr.right = new TreeNode(0);
                stack.push(high);
                stack.push(mid + 1);
                stack.push(curr.right);
            }
            if (low <= mid - 1) {
                curr.left = new TreeNode(0);
                stack.push(mid - 1);
                stack.push(low);
                stack.push(curr.left);
            }
        }
        return root;
    }
}

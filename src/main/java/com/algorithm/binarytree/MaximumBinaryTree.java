package com.algorithm.binarytree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/6/30
 */
public class MaximumBinaryTree {

    public static void main(String[] args) {
        Deque<Integer> stack = new LinkedList<>();
        stack.push(3);
        stack.push(2);
        stack.push(1);
        stack.poll();
        stack.pollLast();
        System.out.println(stack);
    }

    public TreeNode constructMaximumBinaryTreeIter(int[] nums) {
        // 栈：自栈顶到栈底递增 栈底元素为当前子树的根节点 剩下的其他元素为根结点的最右的一条路径上的结点
        Deque<TreeNode> stack = new LinkedList<>();
        // 遍历数组 动态插入节点
        for (int num : nums) {
            // 构造节点
            TreeNode curr = new TreeNode(num);
            // 从最右路径 自下而上 寻找待插入元素的插入位置
            // 1. curr > node curr.left = node 并将node出栈 因为node已经不在最右一条路径上
            // 2. curr < node node.right = curr 把curr放到node的最右路径上
            while (!stack.isEmpty() && num > stack.peek().val) {
                curr.left = stack.pop();
            }
            if (!stack.isEmpty()) {
                stack.peek().right = curr;
            }
            stack.push(curr);
        }
        return stack.removeLast();
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }

    private TreeNode dfs(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        // 找头结点位置
        int maxIndex = getMaxIndex(nums, start, end);
        TreeNode root = new TreeNode(nums[maxIndex]);
        // 划分为两部分 递归求左右子树
        root.left = dfs(nums, start, maxIndex - 1);
        root.right = dfs(nums, maxIndex + 1, end);
        return root;
    }

    private int getMaxIndex(int[] nums, int start, int end) {
        int max = Integer.MIN_VALUE;
        int maxIndex = 0;
        for (int i = start; i <= end; i++) {
            if (nums[i] > max) {
                max = nums[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}

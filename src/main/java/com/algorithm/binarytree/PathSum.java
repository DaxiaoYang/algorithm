package com.algorithm.binarytree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/6/27
 */
public class PathSum {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode left = new TreeNode(4);
        root.left = left;
        TreeNode left1 = new TreeNode(11);
        left.left = left1;
        left1.right = new TreeNode(2);
        left1.left = new TreeNode(7);
        System.out.println(hasPathSumIter(root, 22));
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        // 空结点
        if (root == null) {
            return false;
        }
        // 叶子结点且值刚好等
        if (root.left == null && root.right == null && root.val == targetSum) {
            return true;
        }
        // 只要有任意一条路满足即可 所以用或 改变条件：当前子树所需求的和逐层变小
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }


    public static boolean hasPathSumIter(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        Deque<TreeNode> nodes = new LinkedList<>();
        Deque<Integer> nums = new LinkedList<>();
        nodes.push(root);
        nums.push(targetSum);
        TreeNode currNode;
        int currNum;
        while (!nodes.isEmpty()) {
            currNode = nodes.pop();
            currNum = nums.pop();
            if (currNode.left == null && currNode.right == null && currNum == currNode.val) {
                return true;
            }
            if (currNode.right != null) {
                nodes.push(currNode.right);
                nums.push(currNum - currNode.val);
            }
            if (currNode.left != null) {
                nodes.push(currNode.left);
                nums.push(currNum - currNode.val);
            }
        }
        return false;
    }
}

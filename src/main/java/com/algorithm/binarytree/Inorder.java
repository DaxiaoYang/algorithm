package com.algorithm.binarytree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/6/10
 */
public class Inorder {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        TreeNode son = new TreeNode(2);
        treeNode.right = son;
        son.left = new TreeNode(3);
        System.out.println(inorderTraversalIter(treeNode));
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        inorder(res, root);
        return res;
    }

    private void inorder(List<Integer> res, TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(res, root.left);
        res.add(root.val);
        inorder(res, root.right);
    }

    public static List<Integer> inorderTraversalIter(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        if (root == null) {
            return res;
        }
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }
}

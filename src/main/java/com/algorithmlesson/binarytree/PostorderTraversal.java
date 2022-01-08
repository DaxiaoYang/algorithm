package com.algorithmlesson.binarytree;

import com.algorithm.binarytree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2022/1/5
 */
public class PostorderTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, null, new TreeNode(2, new TreeNode(3), null));
        System.out.println(postorderTraversal(root));
    }

    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        if (root != null) {
            stack.push(root);
        }
        TreeNode curr;
        while (!stack.isEmpty()) {
            curr = stack.pop();
            res.add(0, curr.val);
            if (curr.left != null) {
                stack.push(root.left);
            }
            if (curr.right != null) {
                stack.push(root.right);
            }
        }
        return res;
    }
}

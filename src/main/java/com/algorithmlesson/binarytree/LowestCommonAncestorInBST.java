package com.algorithmlesson.binarytree;

import com.algorithm.binarytree.TreeNode;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2022/1/10
 */
public class LowestCommonAncestorInBST {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        TreeNode left = new TreeNode(2);
        root.left = left;
        TreeNode right = new TreeNode(8);
        root.right = right;
        left.left = new TreeNode(0);
        TreeNode right1 = new TreeNode(4);
        left.right = right1;
        right1.left = new TreeNode(3);
        right1.right = new TreeNode(5);
        right.left = new TreeNode(7);
        right.right = new TreeNode(9);
        System.out.println(lowestCommonAncestor(root, left, right1).val);
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode[] lca = new TreeNode[1];
        getNum(root, p, q, lca);
        return lca[0];
    }

    private static int getNum(TreeNode root, TreeNode p, TreeNode q, TreeNode[] lca) {
        if (root == null) {
            return 0;
        }
        int rootNum = 0;
        int leftNum = 0;
        int rightNum = 0;
        if (p.val < root.val || q.val < root.val) {
            leftNum = getNum(root.left, p, q, lca);
        }
        // 调用返回后立即判断 可以保证是最低的
        if (lca[0] != null) {
            return 2;
        }
        if (p.val > root.val || q.val > root.val) {
            rightNum = getNum(root.right, p, q, lca);
        }
        if (lca[0] != null) {
            return 2;
        }
        if (root == p || root == q) {
            rootNum = 1;
        }
        int sum = rootNum + leftNum + rightNum;
        if (sum == 2) {
            lca[0] = root;
        }
        return sum;
    }

    /**
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode curr = root;
        while (curr != null) {
            // 1. p q都在右子树
            if (p.val > curr.val && q.val > curr.val) {
                curr = curr.right;
            } else if (p.val < curr.val && q.val < curr.val) {
                // 2.p q都在左子树
                curr = curr.left;
            } else {
                // p == root || q == root || p q各在一个子树上
                break;
            }
        }
        return curr;
    }

    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor3(root.left, p, q);
        } else if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor3(root.right, p, q);
        } else {
            return root;
        }
    }
}

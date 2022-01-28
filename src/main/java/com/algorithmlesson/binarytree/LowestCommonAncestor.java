package com.algorithmlesson.binarytree;

import com.algorithm.binarytree.TreeNode;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2022/1/10
 */
public class LowestCommonAncestor {


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode[] lca = new TreeNode[1];
        getNum(root, p, q, lca);
        return lca[0];
    }

    /**
     *
     * @param root
     * @param p
     * @param q
     * @param lca
     * @return 该子树中含有的p和q的数量
     */
    private int getNum(TreeNode root, TreeNode p, TreeNode q, TreeNode[] lca) {
        if (root == null) {
            return 0;
        }
        if (lca[0] != null) {
            return 2;
        }
        int leftNum = getNum(root.left, p, q, lca);
        int rightNum = getNum(root.right, p, q, lca);
        int rootNum = 0;
        if (root == p || root == q) {
            rootNum = 1;
        }
        int sum = rootNum + leftNum + rightNum;
        if (leftNum == 1 && rightNum == 1) {
            lca[0] = root;
            return sum;
        }
        if (leftNum == 1 && (leftNum == 1 || rightNum == 1)) {
            lca[0] = root;
            return sum;
        }
        return sum;
    }
}

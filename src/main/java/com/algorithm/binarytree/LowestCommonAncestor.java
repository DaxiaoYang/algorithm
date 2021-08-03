package com.algorithm.binarytree;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/7/5
 */
public class LowestCommonAncestor {

    /**
     *
     * @param root
     * @param p p和q都是在树中的节点
     * @param q
     * @return 1.如果p和q都在该子树中 返回他们的LCA
     *         2.如果p q只有其中一个在子树中 返回p q
     *         3.如果p q都不在子树中 返回null
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null && right == null) {
            return null;
        } else if (left != null && right != null) {
            return root;
        } else {
            return left == null ? right : left;
        }
    }
}

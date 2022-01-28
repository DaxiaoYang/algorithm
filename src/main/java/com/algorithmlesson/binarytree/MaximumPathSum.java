package com.algorithmlesson.binarytree;

import com.algorithm.binarytree.TreeNode;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2022/1/18
 */
public class MaximumPathSum {


    private int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        getMaxPathLen(root);
        return max;
    }

    /**
     *
     * @param root 1.所求最大路径合的子树
     * @return 2. 子树根节点值 + 其最大子树路径合
     */
    private int getMaxPathLen(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 3. LRN 如果返回负数则直接丢弃 不加入到路径中
        int left = Math.max(getMaxPathLen(root.left), 0);
        int right = Math.max(getMaxPathLen(root.right), 0);
        // 过root这个根节点的最大路径和
        max = Math.max(max, root.val + left + right);
        return root.val + Math.max(left, right);
    }
}

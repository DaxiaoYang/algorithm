package com.algorithm.dynamicprogramming;

import com.algorithm.binarytree.TreeNode;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/9/25
 */
public class HouseRobber3 {

    public int rob(TreeNode root) {
        int[] res = postOrder(root);
        return Math.max(res[0], res[1]);
    }

    /**
     *
     * @param root
     * @return 大小为2的数组 [不选当前结点能获得的最大收入，选当前结点能获取的最大收入] 记录每个结点的两个状态
     */
    private int[] postOrder(TreeNode root) {
        // 递归出口
        if (root == null) {
            return new int[]{0, 0};
        }
        // 因为要用到左右子节点的结果 所以用后序遍历
        int[] left = postOrder(root.left);
        int[] right = postOrder(root.right);
        // 不选择当前结点 就可以考虑是否选择左右孩子
        int notChoose = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        // 选择当前结点 那么左右孩子都不能选了
        int choose = root.val + left[0] + right[0];
        return new int[]{notChoose, choose};
    }
}

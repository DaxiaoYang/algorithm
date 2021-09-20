package com.algorithm.greedy;


import com.algorithm.binarytree.TreeNode;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/9/1
 */
public class BinaryTreeCamera {

    private int res;

    private static final int NOT_COVERED = 0;

    private static final int CAMERA = 1;

    private static final int COVERED = 2;

    public int minCameraCover(TreeNode root) {
        if (postorder(root) == 0) {
            res++;
        }
        return res;
    }

    /**
     * 思路：不在叶子结点放
     * 并且自底向上 隔两个放
     * 为了实现隔两个放 所以设置状态
     * 状态：0 无覆盖
     *      1 有摄像头
     *      2 有覆盖
     */
    private int postorder(TreeNode root) {
        // 空结点状态为有覆盖 所以叶子结点状态为无覆盖
        if (root == null) {
            return 2;
        }
        int left = postorder(root.left);
        int right = postorder(root.right);
        // 只要有一个孩子结点状态为无覆盖 该结点必须要有摄像头
        if (left == NOT_COVERED || right == NOT_COVERED) {
            res++;
            return CAMERA;
        }
        // 两个孩子结点都覆盖到了那该结点就是无覆盖的 等他的父结点设摄像头
        if (left == COVERED && right == COVERED) {
            return NOT_COVERED;
        }
        // 两个孩子结点中有一个是有摄像头 该结点就是覆盖了的 left == 1 || right == 0的情况在第一种
        if (left == CAMERA || right == CAMERA) {
            return COVERED;
        }
        return -1;
    }
}

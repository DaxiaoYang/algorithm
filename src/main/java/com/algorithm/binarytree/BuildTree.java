package com.algorithm.binarytree;

import java.util.HashMap;
import java.util.Map;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/6/29
 */
public class BuildTree {


    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> inValToIndex = new HashMap<>();
        // 构造中序序列中：值 -> index 的映射 预处理 + 哈希的思想 用于后面从中序数组中根据值获取索引
        for (int i = 0; i < inorder.length; i++) {
            inValToIndex.put(inorder[i], i);
        }
        return buildTree(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1, inValToIndex);
    }

    /**
     *
     * @param inStart [inStart, inEnd] 在inorder中为当前子树的结点范围
     * @param inEnd
     * @param postStart [postStart, postEnd] 在postorder中为当前子树的结点范围
     * @param postEnd
     * @return
     */
    private TreeNode buildTree(int[] inorder, int[] postorder, int inStart, int inEnd,
                               int postStart, int postEnd, Map<Integer, Integer> inValToIndex) {
        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }
        // 找到当前子树的头结点在中序的位置
        int headVal = postorder[postEnd];
        int headIndex = inValToIndex.get(headVal);
        TreeNode root = new TreeNode(headVal);
        // 切半 画表 确定当前子树的左子树和右子树的边界关系
        root.left = buildTree(inorder, postorder, inStart, headIndex - 1, postStart, postStart + headIndex - inStart - 1, inValToIndex);
        root.right = buildTree(inorder, postorder, headIndex + 1, inEnd, postStart + headIndex - inStart, postEnd - 1, inValToIndex);
        return root;
    }
}

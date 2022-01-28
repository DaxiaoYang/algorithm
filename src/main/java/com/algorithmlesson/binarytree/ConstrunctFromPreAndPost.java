package com.algorithmlesson.binarytree;

import com.algorithm.binarytree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2022/1/11
 */
public class ConstrunctFromPreAndPost {

    public static void main(String[] args) {
        int[] preorder = {1,2,4,5,3};
        int[] postorder = {4,5,2,3,1};
        TreeNode treeNode = constructFromPrePost(preorder, postorder);
        System.out.println("");
    }

    public static TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        Map<Integer, Integer> map = new HashMap<>();
        int len = postorder.length;
        for (int i = 0; i < len; i++) {
            map.put(postorder[i], i);
        }
        return construct(preorder, postorder, map, 0, len - 1, 0, len - 1);
    }

    private static TreeNode construct(int[] preorder, int[] postorder, Map<Integer, Integer> map, int preStart, int preEnd, int postStart, int postEnd) {
        if (preStart > preEnd || postStart > postEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        if (preStart + 1 <= preEnd) {
            int leftRoot = map.get(preorder[preStart + 1]);
            int leftNums = leftRoot - postStart + 1;
            root.left = construct(preorder, postorder, map, preStart + 1, preStart + leftNums, postStart, leftRoot);
            root.right = construct(preorder, postorder, map, preStart + leftNums + 1, preEnd, leftRoot + 1, postEnd - 1);
        }
        return root;
    }
}

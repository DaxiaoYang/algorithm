package com.algorithm.binarytree;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/7/2
 */
public class SearchBST {

    public TreeNode searchBST(TreeNode root, int val) {
        // 递归边界 空结点（表示没有找到）或者符合条件
        if (root == null || root.val == val) {
            return root;
        }
        // 利用大小信息进行剪枝
        return val < root.val ? searchBST(root.left, val) : searchBST(root.right, val);
    }

    public TreeNode searchBSTIter(TreeNode root, int val) {
        TreeNode curr = root;
        while (curr != null) {
            if (val < curr.val) {
                curr = curr.left;
            } else if (val > curr.val){
                curr = curr.right;
            } else {
                break;
            }
        }
        return curr;
    }
}

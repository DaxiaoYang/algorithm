package com.algorithm.binarytree;

import java.util.LinkedList;
import java.util.List;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/6/21
 */
public class BinaryTreePath {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        root.left = left;
        left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        System.out.println(binaryTreePaths(root));
    }

    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new LinkedList<>();
        dfs(root, new StringBuilder(), res);
        return res;
    }

    private static void dfs(TreeNode root, StringBuilder temp, List<String> res) {
        // 空结点直接返回
        if (root == null) {
            return;
        }
        // 非空结点分两种情况：叶子结点和分支结点
        // 记录下temp刚进入方式时的长度 用于之后的还原（回溯）
        int len = temp.length();
        temp.append(root.val);
        if (root.left == null && root.right == null) {
            res.add(temp.toString());
        } else {
            // 分支结点则向下遍历
            temp.append("->");
            // 走左边
            dfs(root.left, temp, res);
            // 走右边
            dfs(root.right, temp, res);
        }
        // 这里保证了退出方法时 temp的值与进入方法时是一样的
        temp.setLength(len);
    }
}

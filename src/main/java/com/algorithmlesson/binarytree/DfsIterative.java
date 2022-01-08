package com.algorithmlesson.binarytree;

import com.algorithm.binarytree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2022/1/7
 */
public class DfsIterative {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        root.left = left;
        root.right = new TreeNode(3);
        left.left = new TreeNode(4);
        left.right = new TreeNode(5);
        System.out.println(preorderTraversal(root));
    }

    /**
     * 二叉树迭代遍历统一写法
     * 用栈来模拟递归的过程 前中后序遍历 访问结点的顺序都是一样的 只是将结点的值添加到结果列表的时机不一样
     * 每个函数调用产生的栈帧在函数调用栈中都会出现在栈顶3次（出现在栈顶的就是当前线程正在处理的）
     * dfs(root) {
     *     status = 0 遍历左右子树之前
     *     dfs(root.left);
     *     status = 1 遍历完左子树 准备遍历右子树
     *     dfs(root.right);
     *     status = 2 左右子树都已经遍历完了
     * }
     * @param root
     * @return
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Deque<Frame> stack = new LinkedList<>();
        stack.push(new Frame(root));
        while (!stack.isEmpty()) {
            Frame top = stack.peek();
            if (top.status == 0) {
                res.add(top.node.val);
                top.status++;
                if (top.node.left != null) {
                    stack.push(new Frame(top.node.left));
                }
            } else if (top.status == 1) {
                top.status++;
                if (top.node.right != null) {
                    stack.push(new Frame(top.node.right));
                }
            } else {
                stack.pop();
            }
        }
        return res;
    }

    private static class Frame {
        TreeNode node;
        int status;

        Frame(TreeNode node) {
            this.node = node;
        }
    }
}

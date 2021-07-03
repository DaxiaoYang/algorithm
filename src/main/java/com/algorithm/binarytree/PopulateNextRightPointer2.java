package com.algorithm.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/6/16
 */
public class PopulateNextRightPointer2 {

    public static void main(String[] args) {
        NextNode root = new NextNode(1);
        NextNode left = new NextNode(2);
        root.left = left;
        left.left = new NextNode(4);
        left.right = new NextNode(5);
        NextNode right = new NextNode(3);
        root.right = right;
        right.right = new NextNode(7);
        connect(root);
    }

    public static NextNode connect(NextNode root) {
        if (root == null) {
            return null;
        }
        Queue<NextNode> queue = new LinkedList<>();
        queue.offer(root);
        NextNode curr;
        int rowSize;
        while (!queue.isEmpty()) {
            rowSize = queue.size();
            for (int i = 0; i < rowSize; i++) {
                curr = queue.poll();
                if (i != rowSize - 1) {
                    curr.next = queue.peek();
                }
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
        }
        return root;
    }

    public NextNode connect2(NextNode root) {
        NextNode curr = root;
        // 下一层的头结点
        NextNode nextLevelHead = new NextNode(0);
        // 指针 用于把当前遍历层的下一层结点串起来
        NextNode p;
        // 遍历所有层
        while (curr != null) {
            p = nextLevelHead;
            // 遍历当前层
            while (curr != null) {
                if (curr.left != null) {
                    p.next = curr.left;
                    p = p.next;
                }
                if (curr.right != null) {
                    p.next = curr.right;
                    p = p.next;
                }
                curr = curr.next;
            }
            // curr转到下一层 第一个元素结点
            curr = nextLevelHead.next;
            // 关键：清空 next指针的值
            nextLevelHead.next = null;
        }
        return root;
    }
}

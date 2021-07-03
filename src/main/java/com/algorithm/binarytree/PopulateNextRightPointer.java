package com.algorithm.binarytree;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/6/15
 */
public class PopulateNextRightPointer {

    public NextNode connect(NextNode root) {
        if (root == null) {
            return null;
        }
        NextNode nextLevel;
        NextNode curr = root;
        // 最后一层不需要遍历
        while (curr.left != null) {
            // 记录下一层的开始结点
            nextLevel = curr.left;
            // 遍历当层 操作下一层的结点
            while (curr != null) {
                // 改变左孩子的next指针
                curr.left.next = curr.right;
                // 改变右孩子的next指针(最后一个结点不用)
                if (curr.next != null) {
                    curr.right.next = curr.next.left;
                }
                curr = curr.next;
            }
            // 进入下一层
            curr = nextLevel;
        }
        return root;
    }

    public NextNode connectRecur(NextNode root) {
        // 空结点和最后一层的结点不处理
        if (root == null || root.left == null) {
            return root;
        }
        root.left.next = root.right;
        if (root.next != null) {
            root.right.next = root.next.left;
        }
        connectRecur(root.left);
        connectRecur(root.right);
        return root;
    }
}

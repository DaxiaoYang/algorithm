package com.algorithmlesson.sort;

import com.algorithm.linkedlist.ListNode;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2022/1/3
 */
public class SortList {

    /**
     * 1.
     * 参数：待排序的链表
     * 返回值：排序好的链表
     */
    public ListNode sortList(ListNode head) {
        // 2. 终止条件
        if (head == null || head.next == null) {
            return head;
        }
        // 3.单层逻辑 找中点 划分为两个链表
        ListNode prev = null, slow = head, fast = head;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        // 断开
        prev.next = null;
        // 类似于后序遍历 LRN
        ListNode left = sortList(head);
        ListNode right = sortList(slow);
        // 合并两个排好序的链表
        return merge(left, right);
    }

    private ListNode merge(ListNode left, ListNode right) {
        // 因为是链表 所以不用申请额外的存储空间
        ListNode dummy = new ListNode();
        ListNode tail = dummy;
        while (left != null && right != null) {
            if (left.val <= right.val) {
                tail.next = left;
                tail = left;
                left = left.next;
            } else {
                tail.next = right;
                tail = right;
                right = right.next;
            }
        }
        tail.next = left != null ? left : right;
        return dummy.next;
    }


    public static void main(String[] args) {
        ListNode root = new ListNode(-1, new ListNode(5, new ListNode(3, new ListNode(4, new ListNode(0)))));
        sortList2(root);
        System.out.println("");
    }



    public static ListNode sortList2(ListNode head) {
        int step = 1;
        int len = getLength(head);
        while (step < len) {
            ListNode dummy = new ListNode();
            ListNode tail = dummy;
            ListNode curr = head;
            while (curr != null) {
                ListNode start1 = curr;
                ListNode end1 = start1;
                int count = 1;
                while (end1 != null && count < step) {
                    end1 = end1.next;;
                    count++;
                }
                if (end1 == null || end1.next == null) {
                    tail.next = curr;
                    break;
                }
                ListNode start2 = end1.next;
                ListNode end2 = start2;
                count = 1;
                while (end2 != null && count < step) {
                    end2 = end2.next;
                    count++;
                }
                ListNode next = null;
                if (end2 != null) {
                    next = end2.next;
                    end2.next = null;
                }
                end1.next = null;
                ListNode[] headTail = merge2(start1, start2);
                tail.next = headTail[0];
                tail = headTail[1];
                curr = next;
            }
            head = dummy.next;
            step *= 2;
        }
        return head;
    }

    private static ListNode[] merge2(ListNode left, ListNode right) {
        ListNode dummy = new ListNode();
        ListNode tail = dummy;
        while (left != null && right != null) {
            if (left.val <= right.val) {
                tail.next = left;
                left = left.next;
            } else {
                tail.next = right;
                right = right.next;
            }
            tail = tail.next;
        }
        while (left != null) {
            tail.next = left;
            tail = left;
            left = left.next;
        }
        while (right != null) {
            tail.next = right;
            tail = right;
            right = right.next;
        }
        return new ListNode[]{dummy.next, tail};
    }

    private static int getLength(ListNode head) {
        int len = 0;
        ListNode curr = head;
        while (curr != null) {
            curr = curr.next;
            len++;
        }
        return len;
    }
}

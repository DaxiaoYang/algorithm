package com.algorithm.linkedlist;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/5/20
 */
public class SwapNodesInPairs {

    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = dummy, left, right, next;
        while ((left = curr.next) != null && (right = curr.next.next) != null) {
            next = right.next;
            right.next = left;
            left.next = next;
            curr.next = right;
            curr = left;
        }
        return dummy.next;
    }

    public ListNode swapPairsRecur(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 每次处理两个节点
        ListNode next = head.next;
        head.next = swapPairsRecur(head.next.next);
        next.next = head;
        return next;
    }
}

package com.algorithm.linkedlist;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/11/6
 */
public class ReorderList {

    public void reorderList(ListNode head) {
        Deque<ListNode> deque = new LinkedList<>();
        ListNode curr = head;
        while (curr != null) {
            deque.offer(curr);
            curr = curr.next;
        }
        curr = deque.pollFirst();
        int count = 1;
        while (!deque.isEmpty()) {
            if (count % 2 == 1) {
                curr.next = deque.pollLast();
            } else {
                curr.next = deque.pollFirst();
            }
            count++;
            curr = curr.next;
        }
        // 避免出现环
        curr.next = null;
    }


    public void reorderList2(ListNode head) {
        ListNode rightStart = getMiddleNode(head);
        rightStart = reverse(rightStart);
        ListNode curr = head;
        ListNode left = head.next, right = rightStart;
        int count = 1;
        while (left != null && right != null) {
            if (count % 2 == 1) {
                curr.next = right;
                right = right.next;
            } else {
                curr.next = left;
                left = left.next;
            }
            curr = curr.next;
            count++;
        }
        if (left != null) {
            curr.next = left;
            curr = curr.next;
        }
        if (right != null) {
            curr.next = right;
            curr = curr.next;
        }
        curr.next = null;
    }

    private ListNode getMiddleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null, next = null, curr = head;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}

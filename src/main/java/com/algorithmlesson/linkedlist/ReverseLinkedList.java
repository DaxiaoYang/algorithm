package com.algorithmlesson.linkedlist;

import com.algorithm.linkedlist.ListNode;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/12/10
 */
public class ReverseLinkedList {

    public ListNode reverseList(ListNode head) {
        // 头插法反转链表
        ListNode dummy = new ListNode();
        ListNode curr = head;
        ListNode next;
        while (curr != null) {
            next = curr.next;
            curr.next = dummy.next;
            dummy.next = curr;
            curr = next;
        }
        return dummy.next;
    }
}

package com.algorithmlesson.linkedlist;

import com.algorithm.linkedlist.ListNode;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/12/10
 */
public class AddTwoNumber {


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyNode = new ListNode();
        ListNode tail = dummyNode;
        ListNode p1 = l1, p2 = l2;
        int sum;
        int carry = 0;
        while (p1 != null || p2 != null || carry != 0) {
            sum = carry;
            if (p1 != null) {
                sum += p1.val;
                p1 = p1.next;
            }
            if (p2 != null) {
                sum += p2.val;
                p2 = p2.next;
            }
            tail.next = new ListNode(sum % 10);
            tail = tail.next;
            carry = sum / 10;
        }
        return dummyNode.next;
    }
}

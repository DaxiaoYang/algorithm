package com.algorithm.linkedlist;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/11/1
 */
public class RemoveElements {

    public static void main(String[] args) {
        ListNode head = new ListNode(6);
        System.out.println(removeElements(head, 6));
    }

    public static ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = dummy;
        // 处理对象始终是curr.next
        while (curr.next != null) {
            // 7 7 7 的情况
            if (curr.next.val == val) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
        return dummy.next;
    }
}

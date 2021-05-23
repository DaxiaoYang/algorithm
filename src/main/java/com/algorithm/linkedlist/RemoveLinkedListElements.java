package com.algorithm.linkedlist;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/5/19
 */
public class RemoveLinkedListElements {

    public ListNode removeElements(ListNode head, int val) {
        // 建立一个虚拟节点 统一对头节点的操作
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = dummy;
        while (curr.next != null) {
            // 如果符合条件则直接删除
            if (curr.next.val == val) {
                curr.next = curr.next.next;
            } else {
                // 否则继续向后遍历
                curr = curr.next;
            }
        }
        return dummy.next;
    }

    public ListNode removeElementsRecur(ListNode head, int val) {
        // 递归出口
        if (head == null) {
            return null;
        }
        head.next = removeElementsRecur(head.next, val);
        // 每次以当前节点为判断标准 符合条件则删除当前节点
        return head.val == val ? head.next : head;
    }
}

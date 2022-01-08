package com.algorithmlesson.linkedlist;

import com.algorithm.linkedlist.ListNode;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/12/10
 */
public class ReverseNodesInKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode();
        ListNode tail = dummy;
        ListNode start = head;
        ListNode end = head;
        ListNode next;
        int count;
        while (end != null) {
            count = 0;
            // 找到 [start, end]
            while (end != null && ++count < k) {
                end = end.next;
            }
            // 如果不满k 则直接链接上
            if (end == null) {
                tail.next = start;
                break;
            }
            // 断链 保证每次反转的都是一个结尾为null的链表 方便处理
            next = end.next;
            end.next = null;
            ListNode[] range = reverse(start, end);
            tail.next = range[0];
            tail = range[1];
            start = next;
            end = next;
        }
        return dummy.next;
    }

    /**
     * 反转链表 范围为[start, end]
     * @param start 头
     * @param end 尾
     * @return [0]新链表头结点 [1]新链表尾结点
     */
    private ListNode[] reverse(ListNode start, ListNode end) {
        ListNode prev = null;
        ListNode curr = start;
        ListNode next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return new ListNode[]{end, start};
    }
}

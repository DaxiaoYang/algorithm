package com.algorithmlesson.linkedlist;

import com.algorithm.linkedlist.ListNode;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/12/21
 */
public class MergeTwoSortedList {

    /**
     * 1. 参数 返回值
     * @param list1 要合并的链表A的头结点
     * @param list2 要合并的链表B的头结点
     * @return 两个链表合并后链表头结点
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 2.终止条件
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        // 从前向后遍历 （类似于前序遍历 先访问结点 再访问子树 递归反转链表那个类似于后序遍历）
        // 3.单层递归逻辑
        // 每次都选取两个头结点中较小的作为合并后的链表的头结点
        ListNode head;
        ListNode next;
        if (list1.val <= list2.val) {
            head = list1;
            next = list1.next;
            list1.next = null;
            // 选了链表A的头结点 链表A往后移一位
            head.next = mergeTwoLists(next, list2);
        } else {
            head = list2;
            next = list2.next;
            list2.next = null;
            head.next = mergeTwoLists(list1, next);
        }
        // 返回两个链表合并后的链表的头结点
        return head;
    }
}

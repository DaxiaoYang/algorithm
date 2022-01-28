package com.algorithmlesson.heap;

import com.algorithm.linkedlist.ListNode;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2022/1/27
 * 多路归并排序
 * 除了用堆外还可以用归并的思想 自底向上或者自顶向下都可以
 */
public class MergeKSortedList {

    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }
        int mid = start + (end - start) / 2;
        ListNode left = merge(lists, start, mid);
        ListNode right = merge(lists, mid + 1, end);
        return merge(left, right);
    }


    public ListNode mergeKLists(ListNode[] lists) {
        int len = lists.length;
        if (len == 0) {
            return null;
        }
        int newLen;
        while (len > 1) {
            newLen = 0;
            for (int i = 0; i < len - 1; i += 2) {
                lists[newLen++] = merge(lists[i], lists[i + 1]);
            }
            if (len % 2 == 1) {
                lists[newLen++] = lists[len - 1];
            }
            len = newLen;
        }
        return lists[0];
    }

    private ListNode merge(ListNode left, ListNode right) {
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
        tail.next = left != null ? left : right;
        return dummy.next;
    }
}

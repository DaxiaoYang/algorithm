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
        ListNode root = new ListNode(4, new ListNode(2, new ListNode(1, new ListNode(3))));
        sortList2(root);
        System.out.println("");
    }


    /**
     * 自底向上归并合并链表
     * @param head
     * @return
     */
    public static ListNode sortList2(ListNode head) {
        // 本轮要合并的链表的长度
        int step = 1;
        int len = getLength(head);
        while (step < len) {
            // 每轮都是遍历原链表 构造链表
            ListNode dummy = new ListNode();
            ListNode tail = dummy;
            ListNode curr = head;
            while (curr != null) {
                // 合并[low,mid] [mid+1, high]两个链表
                ListNode low = curr;
                ListNode mid = curr;
                int count = 1;
                while (mid != null && count < step) {
                    mid = mid.next;
                    count++;
                }
                // 第一个链表不满step个 或者只有第一个链表 说明本轮归并结束
                // [low, mid) 这部分已经有序 直接加入结果链表中
                if (mid == null || mid.next == null) {
                    tail.next = low;
                    break;
                }
                ListNode high = mid.next;
                count = 1;
                while (high != null && count < step) {
                    high = high.next;
                    count++;
                }
                // 记录下一个要归并的左链表的头结点
                ListNode next = null;
                // high == null 为第二个链表不满step个的情况
                if (high != null) {
                    next = high.next;
                }
                // 返回归并后的结果链表的头和尾
                ListNode[] headAndTail = merge(low, mid, high);
                tail.next = headAndTail[0];
                tail = headAndTail[1];
                curr = next;
            }
            // 更新head
            head = dummy.next;
            // 步长增加
            step *= 2;
        }
        return head;
    }

    private static ListNode[] merge(ListNode low, ListNode mid, ListNode high) {
        ListNode dummy = new ListNode();
        ListNode tail = dummy;
        ListNode left = low, right = mid.next;
        mid.next = null;
        high.next = null;
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
            left = left.next;
            tail = tail.next;
        }
        while (right != null) {
            tail.next = right;
            right = right.next;
            tail = tail.next;
        }
        return new ListNode[]{dummy.next, tail};
    }

    private static int getLength(ListNode head) {
        int len = 0;
        while (head != null) {
            head = head.next;
            len++;
        }
        return len;
    }
}

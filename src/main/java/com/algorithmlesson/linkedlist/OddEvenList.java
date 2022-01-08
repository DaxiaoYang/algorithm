package com.algorithmlesson.linkedlist;

import com.algorithm.linkedlist.ListNode;

/**
 * @ description: 
 * @ author: daxiao
 * @ date: 2021/12/13 
 */
public class OddEvenList {


    public ListNode oddEvenList(ListNode head) {
        // 构造链表的方式 虚拟头结点 + 尾结点
        ListNode evenHead = new ListNode();
        ListNode oddHead = new ListNode();
        ListNode evenTail = evenHead;
        ListNode oddTail = oddHead;
        ListNode curr = head;
        // 每个结点都先断链 从原始链表中剥离出来 再进行处理
        ListNode next;
        int count = 0;
        while (curr != null) {
            count++;
            next = curr.next;
            curr.next = null;
            if (count % 2 == 1) {
                oddTail.next = curr;
                oddTail = curr;
            } else {
                evenTail.next = curr;
                evenTail = curr;
            }
            curr = next;
        }
        // 连接奇链表与偶链表
        oddTail.next = evenHead.next;
        return oddHead.next;
    }
}

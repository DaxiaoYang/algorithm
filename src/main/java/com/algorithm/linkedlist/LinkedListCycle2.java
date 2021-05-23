package com.algorithm.linkedlist;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/5/21
 *
 */
public class LinkedListCycle2 {

    /*
     * When fast and slow meet at point p, the length they have run are 'a+2b+c' and 'a+b'.
     * Since the fast is 2 times faster than the slow. So a+2b+c == 2(a+b), then we get 'a==c'.
     * So when another slow2 pointer run from head to 'q', at the same time, previous slow pointer will run from 'p' to 'q', so they meet at the pointer 'q' together.
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        // 先找到相遇点
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        if (fast == null || fast.next == null) {
            return null;
        }
        // 其中一个指针指向链表头部
        fast = head;
        // 因为a = c 所以一起相后移动就能找到入口
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }
}

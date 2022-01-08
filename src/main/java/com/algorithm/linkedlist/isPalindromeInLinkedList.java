package com.algorithm.linkedlist;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/9/27
 * 字符串存在链表中 判断字符串是否是回文串
 */
public class isPalindromeInLinkedList {

    public static void main(String[] args) {
        CharNode head = new CharNode('a');
        CharNode next = new CharNode('b');
        head.next = next;
        CharNode next1 = new CharNode('c');
        next.next = next1;
        next1.next = new CharNode('a');
        System.out.println(isPalindrome(head));
    }

    public static boolean isPalindrome(CharNode head) {
        if (head == null) {
            return true;
        }
        CharNode[] middleRights = getMiddleRight(head);
        CharNode middleRight = middleRights[1];
        middleRight = reverse(middleRight);
        CharNode right = middleRight;
        CharNode left = head;
        while (left != null && right != null) {
            if (left.c != right.c) {
                return false;
            }
            left = left.next;
            right = right.next;
        }
        middleRights[0].next = reverse(middleRight);
        return true;
    }

    private static CharNode[] getMiddleRight(CharNode head) {
        CharNode prev = null;
        CharNode slow = head;
        CharNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }
        return new CharNode[]{prev, slow};
    }

    private static CharNode reverse(CharNode head) {
        CharNode curr = head;
        CharNode prev = null;
        CharNode next = null;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static class CharNode {
        char c;
        CharNode next;

        public CharNode(char c, CharNode next) {
            this.c = c;
            this.next = next;
        }

        public CharNode(char c) {
            this.c = c;
        }
    }
}

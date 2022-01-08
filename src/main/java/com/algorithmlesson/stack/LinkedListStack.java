package com.algorithmlesson.stack;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/12/14
 */
public class LinkedListStack {

    private StackNode head;

    public void push(int val) {
        if (head == null) {
            head = new StackNode(val, null);
        } else {
            head = new StackNode(val, head);
        }
    }

    public int pop() {
        if (head == null) {
            return -1;
        } else {
            int res = head.val;
            head = head.next;
            return res;
        }
    }


    private class StackNode {
        int val;
        StackNode next;

        public StackNode(int val, StackNode next) {
            this.val = val;
            this.next = next;
        }
    }


}

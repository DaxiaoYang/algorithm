package com.algorithmlesson.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/12/15
 */
class SortedStack {

    private Deque<Integer> stack = new LinkedList<>();

    private Deque<Integer> temp = new LinkedList<>();

    public SortedStack() {

    }

    public void push(int val) {
        while (!stack.isEmpty() && stack.peek() < val) {
            temp.push(stack.pop());
        }
        stack.push(val);
        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }
    }

    public void pop() {
        if (isEmpty()) {
            return;
        }
        stack.pop();
    }

    public int peek() {
        if (isEmpty()) {
            return -1;
        }
        return stack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}

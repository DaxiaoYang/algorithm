package com.algorithm.stack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/6/2
 */
public class ImplementQueueUsingStacks {

    private Deque<Integer> input = new LinkedList<>();

    private Deque<Integer> output = new LinkedList<>();

    /** 用于记录队头元素 */
    private int front;

    /** Initialize your data structure here. */
    public ImplementQueueUsingStacks() {

    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        if (input.isEmpty()) {
            front = x;
        }
        input.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (!output.isEmpty()) {
            return output.pop();
        }
        if (!input.isEmpty()) {
            while (!input.isEmpty()) {
                output.push(input.pop());
            }
            return output.pop();
        }
        return -1;
    }

    /** Get the front element. */
    public int peek() {
        if (!output.isEmpty()) {
            return output.peek();
        }
        if (!input.isEmpty()) {
            return front;
        }
        return -1;
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return output.isEmpty() && input.isEmpty();
    }
}

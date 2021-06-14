package com.algorithm.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/6/2
 */
public class ImplementStackUsingQueue {

    private Queue<Integer> queue = new LinkedList<>();

    /** Initialize your data structure here. */
    public ImplementStackUsingQueue() {

    }

    /** Push element x onto stack. */
    public void push(int x) {
        // 先将元素入队
        queue.offer(x);
        // 将剩余的元素 即在x前面入队的元素 全部出队再入队 保证刚进来的x 能第一个出"栈"
        // 如果是第一个插入的则不用进行操作
        for (int i = 1; i < queue.size(); i++) {
            queue.offer(queue.poll());
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue.poll();
    }

    /** Get the top element. */
    public int top() {
        return queue.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}

package com.algorithmlesson.stack;

import java.util.Stack;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/12/15
 *
 *
 */
class TripleInOne {
    /**
     * 栈个数
     */
    private int stackCount = 3;
    /**
     * 每个栈的栈顶指针 如top[0] 指向第一个栈的元素要插入的位置
     */
    private int[] top = new int[stackCount];

    private int[] values;

    private int stackSize;

    public TripleInOne(int stackSize) {
        this.stackSize = stackSize;
        values = new int[stackCount * stackSize];
        // 栈空 base[i] == top[i]
        for (int i = 0; i < stackCount; i++) {
            top[i] = i * stackSize;
        }
    }

    public void push(int stackNum, int value) {
        if (top[stackNum] == (stackNum + 1) * stackSize) {
            return;
        }
        values[top[stackNum]++] = value;
    }

    public int pop(int stackNum) {
        if (isEmpty(stackNum)) {
            return -1;
        }
        return values[--top[stackNum]];
    }

    public int peek(int stackNum) {
        if (isEmpty(stackNum)) {
            return -1;
        }
        return values[top[stackNum] - 1];
    }

    public boolean isEmpty(int stackNum) {
        return top[stackNum] == stackNum * stackSize;
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
    }
}

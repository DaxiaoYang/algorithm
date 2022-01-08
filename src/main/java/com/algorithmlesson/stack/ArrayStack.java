package com.algorithmlesson.stack;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/12/14
 */
public class ArrayStack {

    private final int[] data;

    private int count;

    private final int capacity;

    public ArrayStack(int capacity) {
        data = new int[capacity];
        this.capacity = capacity;
    }

    public boolean push(int val) {
        if (count == capacity) {
            return false;
        }
        data[count++] = val;
        return true;
    }

    public int pop() {
        if (count == 0) {
            return -1;
        }
        return data[--count];
    }
}

package com.algorithmlesson.queue;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/12/14
 */
public class CircularQueue {

    private final int[] data;

    private int head;

    private int tail;

    private final int capacity;

    public CircularQueue(int capacity) {
        this.capacity = capacity;
        data = new int[capacity];
    }

    public boolean offer(int val) {
        // 队满 (tail + 1) % capacity == head
        if ((tail + 1) % capacity == head) {
            return false;
        }
        data[tail] = val;
        tail = (tail + 1) % capacity;
        return true;
    }

    public int poll() {
        // 队空 head == tail
        if (head == tail) {
            return -1;
        }
        int res = data[head];
        head = (head + 1) % capacity;
        return res;
    }

    public static void main(String[] args) {
        CircularQueue queue = new CircularQueue(4);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        queue.offer(4);
        queue.offer(5);
        queue.offer(6);
    }
}
